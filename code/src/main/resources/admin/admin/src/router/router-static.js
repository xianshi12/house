import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import houseType from '@/views/modules/houseType/list'
	import news from '@/views/modules/news/list'
	import discusspropertyInfo from '@/views/modules/propertyComment/list'
	import propertyInfo from '@/views/modules/propertyInfo/list'
	import developer from '@/views/modules/developer/list'
	import contractSigning from '@/views/modules/contractSigning/list'
	import user from '@/views/modules/user/list'
	import messages from '@/views/modules/messages/list'
	import viewingReservation from '@/views/modules/viewingReservation/list'
	import config from '@/views/modules/config/list'
	import intentDepositPayment from '@/views/modules/intentDepositPayment/list'
	import newsType from '@/views/modules/newsType/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/houseType',
		name: '房屋类型',
		component: houseType
	}
	,{
		path: '/fangwuleixing',
		name: 'houseTypeAlias',
		component: houseType
	}
	,{
		path: '/news',
		name: '售房资讯',
		component: news
	}
	,{
		path: '/discusspropertyInfo',
		name: '房源信息评论',
		component: discusspropertyInfo
	}
	,{
		path: '/discussfangyuanxinxi',
		name: 'propertyCommentAlias',
		component: discusspropertyInfo
	}
	,{
		path: '/propertyInfo',
		name: '房源信息',
		component: propertyInfo
	}
	,{
		path: '/fangyuanxinxi',
		name: 'propertyInfoAlias',
		component: propertyInfo
	}
	,{
		path: '/developer',
		name: '开发商',
		component: developer
	}
	,{
		path: '/kaifashang',
		name: 'developerAlias',
		component: developer
	}
	,{
		path: '/contractSigning',
		name: '合同签订',
		component: contractSigning
	}
	,{
		path: '/hetongqianding',
		name: 'contractSigningAlias',
		component: contractSigning
	}
	,{
		path: '/user',
		name: '用户',
		component: user
	}
	,{
		path: '/yonghu',
		name: 'userAlias',
		component: user
	}
	,{
		path: '/messages',
		name: '投诉建议',
		component: messages
	}
	,{
		path: '/viewingReservation',
		name: '看房预约',
		component: viewingReservation
	}
	,{
		path: '/kanfangyuyue',
		name: 'viewingReservationAlias',
		component: viewingReservation
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/intentDepositPayment',
		name: '意向金支付',
		component: intentDepositPayment
	}
	,{
		path: '/yixiangjinzhifu',
		name: 'intentDepositPaymentAlias',
		component: intentDepositPayment
	}
	,{
		path: '/newsType',
		name: '售房资讯分类',
		component: newsType
	}
	,{
		path: '/newstype',
		name: 'newsTypeAlias',
		component: newsType
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
