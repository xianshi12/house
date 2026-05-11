import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Messages from '../pages/messages/list'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import userList from '../pages/user/list'
import userDetail from '../pages/user/detail'
import userAdd from '../pages/user/add'
import developerList from '../pages/developer/list'
import developerDetail from '../pages/developer/detail'
import developerAdd from '../pages/developer/add'
import houseTypeList from '../pages/houseType/list'
import houseTypeDetail from '../pages/houseType/detail'
import houseTypeAdd from '../pages/houseType/add'
import propertyInfoList from '../pages/propertyInfo/list'
import propertyInfoDetail from '../pages/propertyInfo/detail'
import propertyInfoAdd from '../pages/propertyInfo/add'
import viewingReservationList from '../pages/viewingReservation/list'
import viewingReservationDetail from '../pages/viewingReservation/detail'
import viewingReservationAdd from '../pages/viewingReservation/add'
import intentDepositPaymentList from '../pages/intentDepositPayment/list'
import intentDepositPaymentDetail from '../pages/intentDepositPayment/detail'
import intentDepositPaymentAdd from '../pages/intentDepositPayment/add'
import contractSigningList from '../pages/contractSigning/list'
import contractSigningDetail from '../pages/contractSigning/detail'
import contractSigningAdd from '../pages/contractSigning/add'
import newsTypeList from '../pages/newsType/list'
import newsTypeDetail from '../pages/newsType/detail'
import newsTypeAdd from '../pages/newsType/add'
import discusspropertyInfoList from '../pages/propertyComment/list'
import discusspropertyInfoDetail from '../pages/propertyComment/detail'
import discusspropertyInfoAdd from '../pages/propertyComment/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'messages',
					component: Messages
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'user',
					component: userList
				},
				{
					path: 'yonghu',
					component: userList
				},
				{
					path: 'userDetail',
					component: userDetail
				},
				{
					path: 'yonghuDetail',
					component: userDetail
				},
				{
					path: 'userAdd',
					component: userAdd
				},
				{
					path: 'yonghuAdd',
					component: userAdd
				},
				{
					path: 'developer',
					component: developerList
				},
				{
					path: 'kaifashang',
					component: developerList
				},
				{
					path: 'developerDetail',
					component: developerDetail
				},
				{
					path: 'kaifashangDetail',
					component: developerDetail
				},
				{
					path: 'developerAdd',
					component: developerAdd
				},
				{
					path: 'kaifashangAdd',
					component: developerAdd
				},
				{
					path: 'houseType',
					component: houseTypeList
				},
				{
					path: 'fangwuleixing',
					component: houseTypeList
				},
				{
					path: 'houseTypeDetail',
					component: houseTypeDetail
				},
				{
					path: 'fangwuleixingDetail',
					component: houseTypeDetail
				},
				{
					path: 'houseTypeAdd',
					component: houseTypeAdd
				},
				{
					path: 'fangwuleixingAdd',
					component: houseTypeAdd
				},
				{
					path: 'propertyInfo',
					component: propertyInfoList
				},
				{
					path: 'fangyuanxinxi',
					component: propertyInfoList
				},
				{
					path: 'propertyInfoDetail',
					component: propertyInfoDetail
				},
				{
					path: 'fangyuanxinxiDetail',
					component: propertyInfoDetail
				},
				{
					path: 'propertyInfoAdd',
					component: propertyInfoAdd
				},
				{
					path: 'fangyuanxinxiAdd',
					component: propertyInfoAdd
				},
				{
					path: 'viewingReservation',
					component: viewingReservationList
				},
				{
					path: 'kanfangyuyue',
					component: viewingReservationList
				},
				{
					path: 'viewingReservationDetail',
					component: viewingReservationDetail
				},
				{
					path: 'kanfangyuyueDetail',
					component: viewingReservationDetail
				},
				{
					path: 'viewingReservationAdd',
					component: viewingReservationAdd
				},
				{
					path: 'kanfangyuyueAdd',
					component: viewingReservationAdd
				},
				{
					path: 'intentDepositPayment',
					component: intentDepositPaymentList
				},
				{
					path: 'yixiangjinzhifu',
					component: intentDepositPaymentList
				},
				{
					path: 'intentDepositPaymentDetail',
					component: intentDepositPaymentDetail
				},
				{
					path: 'yixiangjinzhifuDetail',
					component: intentDepositPaymentDetail
				},
				{
					path: 'intentDepositPaymentAdd',
					component: intentDepositPaymentAdd
				},
				{
					path: 'yixiangjinzhifuAdd',
					component: intentDepositPaymentAdd
				},
				{
					path: 'contractSigning',
					component: contractSigningList
				},
				{
					path: 'hetongqianding',
					component: contractSigningList
				},
				{
					path: 'contractSigningDetail',
					component: contractSigningDetail
				},
				{
					path: 'hetongqiandingDetail',
					component: contractSigningDetail
				},
				{
					path: 'contractSigningAdd',
					component: contractSigningAdd
				},
				{
					path: 'hetongqiandingAdd',
					component: contractSigningAdd
				},
				{
					path: 'newsType',
					component: newsTypeList
				},
				{
					path: 'newstype',
					component: newsTypeList
				},
				{
					path: 'newsTypeDetail',
					component: newsTypeDetail
				},
				{
					path: 'newstypeDetail',
					component: newsTypeDetail
				},
				{
					path: 'newsTypeAdd',
					component: newsTypeAdd
				},
				{
					path: 'newstypeAdd',
					component: newsTypeAdd
				},
				{
					path: 'discusspropertyInfo',
					component: discusspropertyInfoList
				},
				{
					path: 'discussfangyuanxinxi',
					component: discusspropertyInfoList
				},
				{
					path: 'discusspropertyInfoDetail',
					component: discusspropertyInfoDetail
				},
				{
					path: 'discussfangyuanxinxiDetail',
					component: discusspropertyInfoDetail
				},
				{
					path: 'discusspropertyInfoAdd',
					component: discusspropertyInfoAdd
				},
				{
					path: 'discussfangyuanxinxiAdd',
					component: discusspropertyInfoAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
