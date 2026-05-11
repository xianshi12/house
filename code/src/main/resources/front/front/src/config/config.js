export default {
	baseUrl: 'http://localhost:8080/house/',
	name: '/house',
	indexNav: [
		{
			name: '房源信息',
			url: '/index/propertyInfo',
		},
		{
			name: '售房资讯',
			url: '/index/news'
		},
		{
			name: '投诉建议',
			url: '/index/messages'
		},
	],
	cateList: [
		{
			name: '房源信息',
			refTable: 'houseType',
			refColumn: 'houseType',
		},
		{
			name: '售房资讯',
			refTable: 'newsType',
			refColumn: 'typename',
		},
	]
}
