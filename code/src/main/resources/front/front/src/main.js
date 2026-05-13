import Vue from 'vue'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router/router'
import BaiduMap from 'vue-baidu-map'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import '@/assets/css/iconfont.css'
import config from './config/config'
import validate from './common/validate'

import {
	isAuth,
	getCurDateTime,
	getCurDate,
	isBackAuth,
} from './common/system'
import App from './App.vue'
import Breadcrumb from '@/components/Breadcrumb'
import FileUpload from '@/components/FileUpload'
import Editor from "@/components/Editor";
import aplayer from 'vue-aplayer';
import store from './store'
import { encryptDes,decryptDes,encryptAes,decryptAes } from '@/common/des.js'
import VueLuckyCanvas from '@lucky-canvas/vue'
Vue.use(VueLuckyCanvas)
Vue.config.productionTip = false;

Vue.prototype.$config = config;
Vue.prototype.$validate = validate;
Vue.prototype.isAuth = isAuth;
Vue.prototype.isBackAuth = isBackAuth;
Vue.prototype.getCurDateTime = getCurDateTime;
Vue.prototype.getCurDate = getCurDate;
Vue.prototype.encryptDes = encryptDes
Vue.prototype.decryptDes = decryptDes
Vue.prototype.encryptAes = encryptAes
Vue.prototype.decryptAes = decryptAes

Vue.use(VueRouter);
Vue.use(VueResource);
Vue.use(ElementUI);
Vue.use(BaiduMap, {});
Vue.use(VueQuillEditor);

Vue.component('Breadcrumb', Breadcrumb);
Vue.component('file-upload', FileUpload);
Vue.component('editor', Editor);
Vue.component('aplayer', aplayer);

Vue.http.options.root = config.name;
Vue.http.headers.common['Token'] = localStorage.getItem('frontToken');
let frontRefreshingToken = null;
function decodeJwt(token) {
	try {
		const payload = token.split('.')[1];
		return JSON.parse(decodeURIComponent(escape(window.atob(payload.replace(/-/g, '+').replace(/_/g, '/')))));
	} catch (e) {
		return null;
	}
}
function shouldRefreshToken(token) {
	const payload = decodeJwt(token);
	return payload && payload.exp && payload.exp * 1000 - Date.now() < 30000;
}
function clearFrontLogin(routerInstance) {
	localStorage.clear();
	Vue.http.headers.common['Token'] = '';
	routerInstance.replace('/login').catch(err => {});
}
function refreshFrontToken() {
	const refreshToken = localStorage.getItem('frontRefreshToken');
	const tableName = localStorage.getItem('UserTableName') || localStorage.getItem('frontSessionTable');
	if (!refreshToken || !tableName) {
		return Promise.reject();
	}
	if (!frontRefreshingToken) {
		frontRefreshingToken = Vue.http.get(`${tableName}/refresh`, {
			params: { refreshToken },
			headers: { Token: '' }
		}).then(res => {
			if (res.data && res.data.code === 0 && res.data.token) {
				localStorage.setItem('frontToken', res.data.token);
				if (res.data.refreshToken) {
					localStorage.setItem('frontRefreshToken', res.data.refreshToken);
				}
				Vue.http.headers.common['Token'] = res.data.token;
				return res.data.token;
			}
			return Promise.reject();
		}).finally(() => {
			frontRefreshingToken = null;
		});
	}
	return frontRefreshingToken;
}
Vue.http.interceptors.push(function(request, next) {
	const token = localStorage.getItem('frontToken');
	const isAuthRequest = request.url.indexOf('/login') > -1 || request.url.indexOf('/refresh') > -1;
	const continueRequest = (nextToken) => {
		if (nextToken) {
			request.headers.set('Token', nextToken);
		}
		next((response) => {
			if ((response.data.code == 401 || response.data.code == 403) && !isAuthRequest) {
				return refreshFrontToken().then(newToken => {
					response.request.headers.set('Token', newToken);
					return Vue.http(response.request);
				}).catch(() => {
					clearFrontLogin(router);
					return response;
				});
			}
			return response;
		});
	};
	if (!isAuthRequest && token && shouldRefreshToken(token)) {
		refreshFrontToken().then(continueRequest).catch(() => {
			clearFrontLogin(router);
			continueRequest('');
		});
	} else {
		continueRequest(token);
	}
});

router.afterEach((to, from) => {
	if (from.path == '/login') {
		Vue.http.headers.common['Token'] = localStorage.getItem('frontToken');
	}
})

new Vue({
	render: h => h(App),
	router,
	store,
}).$mount('#app')
