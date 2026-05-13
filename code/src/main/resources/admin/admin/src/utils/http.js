import axios from 'axios'
import router from '@/router/router-static'
import storage from '@/utils/storage'
import {Message} from 'element-ui'

const http = axios.create({
	timeout: 1000 * 86400,
	withCredentials: false,
	baseURL: '/house',
	headers: {
		'Content-Type': 'application/json; charset=utf-8'
	}
})
let refreshTokenRequest = null
function clearLogin() {
	storage.clear()
	router.push({ name: 'login' })
}
function refreshAccessToken() {
	const refreshToken = storage.get('RefreshToken')
	const sessionTable = storage.get('sessionTable')
	if (!refreshToken || !sessionTable) {
		return Promise.reject()
	}
	if (!refreshTokenRequest) {
		refreshTokenRequest = axios({
			url: `/house/${sessionTable}/refresh`,
			method: 'get',
			params: { refreshToken }
		}).then(({ data }) => {
			if (data && data.code === 0 && data.token) {
				storage.set('Token', data.token)
				if (data.refreshToken) {
					storage.set('RefreshToken', data.refreshToken)
				}
				return data.token
			}
			return Promise.reject()
		}).finally(() => {
			refreshTokenRequest = null
		})
	}
	return refreshTokenRequest
}
// 请求拦截
http.interceptors.request.use(config => {
	config.headers['Token'] = storage.get('Token') // 请求头带上token
	return config
}, error => {
	return Promise.reject(error)
})
// 响应拦截
http.interceptors.response.use(response => {
	if (response.data && response.data.code === 401) { // 401, token失效
		const config = response.config
		if (!config._retry && config.url.indexOf('/refresh') === -1) {
			config._retry = true
			return refreshAccessToken().then(token => {
				config.headers['Token'] = token
				return http(config)
			}).catch(() => {
				clearLogin()
				return response
			})
		}
		clearLogin()
	}
	return response
}, error => {
	return Promise.reject(error)
})
export default http
