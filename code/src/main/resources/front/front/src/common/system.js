import menu from '@/config/menu'
const tableAlias = {
	fangwuleixing: 'houseType',
	fangyuanxinxi: 'propertyInfo',
	kanfangyuyue: 'viewingReservation',
	yixiangjinzhifu: 'intentDepositPayment',
	hetongqianding: 'contractSigning',
	discussfangyuanxinxi: 'propertyComment',
	newstype: 'newsType',
	kaifashang: 'developer',
	yonghu: 'user',
}
function normalizeTableName(tableName) {
	return tableAlias[tableName] || tableName
}
export function isAuth(tableName, key) {
	tableName = normalizeTableName(tableName)
	let role = normalizeTableName(localStorage.getItem("UserTableName"));
	let menus = menu.list()
	for(let i=0;i<menus.length;i++){
		if(normalizeTableName(menus[i].tableName)==role){
			for(let j=0;j<menus[i].frontMenu.length;j++){
				for(let k=0;k<menus[i].frontMenu[j].child.length;k++){
					if(tableName==normalizeTableName(menus[i].frontMenu[j].child[k].tableName)){
						let buttons = menus[i].frontMenu[j].child[k].buttons.join(',');
						return buttons.indexOf(key) !== -1 || false
					}
				}
			}
		}
	}
	return false;
}
export function isBackAuth(tableName, key) {
	tableName = normalizeTableName(tableName)
	let role = normalizeTableName(localStorage.getItem("UserTableName"));
	let menus = menu.list()
	for(let i=0;i<menus.length;i++){
		if(normalizeTableName(menus[i].tableName)==role){
			for(let j=0;j<menus[i].backMenu.length;j++){
				for(let k=0;k<menus[i].backMenu[j].child.length;k++){
					if(tableName==normalizeTableName(menus[i].backMenu[j].child[k].tableName)){
						let buttons = menus[i].backMenu[j].child[k].buttons.join(',');
						return buttons.indexOf(key) !== -1 || false
					}
				}
			}
		}
	}
	return false;
}
/**
 *  * 获取当前时间（yyyy-MM-dd hh:mm:ss）
 *   */
export function getCurDateTime() {
	let currentTime = new Date(),
	year = currentTime.getFullYear(),
	month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1,
	day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate(),
	hour = currentTime.getHours() < 10 ? '0' + currentTime.getHours() : currentTime.getHours(),
	minute = currentTime.getMinutes() < 10 ? '0' + currentTime.getMinutes() : currentTime.getMinutes(),
	second = currentTime.getSeconds() < 10 ? '0' + currentTime.getSeconds() : currentTime.getSeconds();
	return year + "-" + month + "-" + day + " " +hour +":" +minute+":"+second;
}

/**
 *  * 获取当前日期（yyyy-MM-dd）
 *   */
export function getCurDate() {
	let currentTime = new Date(),
	year = currentTime.getFullYear(),
	month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1,
	day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate();
	return year + "-" + month + "-" + day;
}
