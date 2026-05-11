<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'/'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
	
		<div class="news-preview-pv">
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="search-item">
					<el-input v-model="title" placeholder="标题"></el-input>
				</el-form-item>
				<el-button class="search-btn" type="primary" @click="getNewsList(1)">
					<span class="icon iconfont icon-chakan5"></span>
					搜索
				</el-button>
			</el-form>
			
			<!-- category -->
			<div class="category-list">
				<div class="item" @click="categoryClick(0)" :class="categoryIndex == 0 ? 'active' : ''">全部</div>
				<div v-for="(item,index) in categoryList" @click="categoryClick(index+1)" :key="index" class="item" :class="categoryIndex == index+1 ? 'active' : ''">{{item.typename}}</div>
			</div>
			<!-- 样式一 -->
			<div class="list1 index-pv1">
				<div v-for="item in newsList" :key="item.id" @click="toNewsDetail(item)" class="list-item animation-box">
					<img :src="baseUrl + item.picture" >
					<div class="name">{{item.title}}</div>
					<div class="desc">{{item.introduction}}</div>
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{item.addtime}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont icon-geren16"></span>
						<span class="label">发布人：</span>
						<span class="text">{{item.name}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont icon-zan10"></span>
						<span class="label">点赞数：</span>
						<span class="text">{{item.thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{item.storeupnum}}</span>
					</div>
					<div class="view_item">
						<span class="icon iconfont icon-chakan9"></span>
						<span class="label">点击量：</span>
						<span class="text">{{item.clicknum}}</span>
					</div>
				</div>
			</div>
		
			<el-pagination
				background
				id="pagination" class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				:page-sizes="pageSizes"
				prev-text="上一页"
				next-text="下一页"
				:hide-on-single-page="false"
				:layout='["total","prev","pager","next","sizes","jumper"].join()'
				:total="total"
				@current-change="curChange"
				@size-change="sizeChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>

			<!-- 热门信息 -->
			<div class="hot">
				<div class="hot-title">热门信息</div>
				<div class="hot-list">
					<div class="hot-item" v-for="item in hotList" :key="item.id" @click="toNewsDetail(item)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="hot-name">{{ item.title }}</div>
						<div class="hot-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
			<div class="idea1"></div>
		</div>
	</div>
</template>

<script>
	export default {
		//数据集合
		data() {
			return {
				baseUrl: this.$config.baseUrl,
				breadcrumbItem: [
				  {
					name: '售房资讯'
				  }
				],
				newsList: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1,
				layouts: '',
				title: '',
				categoryIndex: 0,
				categoryList: [],
				hotList: [],
			}
		},
		created() {
			this.getCategoryList()
			
			this.getHotList()
		},
		watch:{
			$route(newValue){
				this.getCategoryList()
			}
		},
		//方法集合
		methods: {
			getCategoryList(){
				this.$http.get('newstype/list', {params: {sort: 'typename',order: 'desc'}}).then(res => {
					if (res.data.code == 0) {
						this.categoryList = res.data.data.list;
						if(this.$route.query.homeFenlei){
							for(let i=0;i<this.categoryList.length;i++) {
								if(this.$route.query.homeFenlei == this.categoryList[i].typename) {
									this.categoryIndex = i + 1;
									const currentRoute = this.$route;
									const routeWithoutQuery = { ...currentRoute };
									delete routeWithoutQuery.query;
									this.$router.replace(routeWithoutQuery)
									break;
								}
							}
						}
						this.getNewsList(1);
					}
				});
			},
			categoryClick(index) {
				this.categoryIndex = index
				this.getNewsList()
			},
			getNewsList(page) {
				let params = {page, limit: this.pageSize,sort:'addtime',order:'desc'};
				let searchWhere = {};
				if(this.title != '') searchWhere.title = '%' + this.title + '%';
				if(this.categoryIndex!=0){
					searchWhere.typename = this.categoryList[this.categoryIndex - 1].typename
				}
				this.$http.get('news/list', {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			getHotList(){
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get('news/autoSort', {params: params}).then(res => {
					if (res.data.code == 0) {
						this.hotList = res.data.data.list;
					}
				});
			},
			curChange(page) {
				this.getNewsList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getNewsList(1);
			},
			prevClick(page) {
				this.getNewsList(page);
			},
			nextClick(page) {
				this.getNewsList(page);
			},
			toNewsDetail(item) {
				this.$router.push({path: '/index/newsDetail', query: {id: item.id}});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.news-preview-pv {
				margin: 0px auto;
				color: #333;
				background: none;
				display: flex;
				width: 1200px;
				font-size: 16px;
				justify-content: flex-start;
				align-items: flex-start;
				position: relative;
				flex-wrap: wrap;
				.list-form-pv {
						padding: 10px;
						background: none;
						display: flex;
						width: 100%;
						justify-content: center;
						align-items: center;
						flex-wrap: wrap;
						height: auto;
						.search-item {
								margin: 0 0px;
								.el-input {
										width: 100%;
									}
				.el-input ::v-deep  .el-input__inner {
										border: 1px solid #ccc;
										border-radius: 0px 0 0 0px;
										padding: 0 10px;
										margin: 0;
										color: #333;
										width: auto;
										font-size: 16px;
										border-width: 1px 0 1px 1px;
										line-height: 42px;
										min-width: 350px;
										height: 42px;
									}
			}
			.search-btn {
								cursor: pointer;
								border: 0;
								border-radius: 0 0px 0px 0;
								padding: 0px 15px;
								margin: 0 10px 0 0;
								color: #fff;
								background: #0066D4;
								width: auto;
								font-size: inherit;
								line-height: 42px;
								height: 42px;
								.icon {
										margin: 0 3px 0 0;
										color: #fff;
										font-size: inherit;
									}
			}
		}
		.category-list {
						padding: 0px;
						margin: 40px 0px 0 0;
						background: #fff;
						display: flex;
						width: 100%;
						justify-content: center;
						flex-wrap: wrap;
						height: auto;
						order: 0;
						.item {
								cursor: pointer;
								border: 0px solid #475a8350;
								padding: 10px 20px 10px 26px;
								margin: 0 10px 20px 0;
								color: inherit;
								display: flex;
								font-size: 16px;
								flex-wrap: wrap;
								border-radius: 6px;
								background: #eee;
								justify-content: center;
								align-items: center;
								min-width: 110px;
							}
			
			.item:hover {
								color: #fff;
								background: #0066D4;
							}
			
			.item.active {
								border-radius: 6px;
								color: #fff;
								background: #0066D4;
								font-size: 16px;
							}
		}
		.list1 {
						padding: 0;
						margin: 20px auto;
						color: #888;
						display: flex;
						font-size: 14px;
						border-color: #ddd;
						flex-wrap: wrap;
						background: #fff;
						width: 100%;
						border-width: 1px 0 0 1px;
						border-style: solid;
						height: auto;
						order: 2;
						.list-item {
								cursor: pointer;
								padding: 10px;
								margin: 0;
								background: #fff;
								width: calc(33.33% - 0px);
								border-color: #ddd;
								border-width: 0 1px 1px 0;
								border-style: solid;
								height: auto;
								img {
										margin: 0;
										object-fit: cover;
										width: 160px;
										float: left;
										height: 120px;
									}
				.name {
										padding: 0 12px;
										overflow: hidden;
										color: #333;
										white-space: nowrap;
										font-weight: 600;
										width: calc(100% - 160px);
										font-size: 15px;
										line-height: 1.5;
										text-overflow: ellipsis;
										float: right;
									}
				.desc {
										padding: 5px 10px;
										max-height: 96px;
										overflow: hidden;
										color: #666;
										width: calc(100% - 160px);
										font-size: 14px;
										line-height: 24px;
										float: right;
									}
				.time_item {
										padding: 0 10px;
										display: none;
										.icon {
												margin: 0 2px 0 0;
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
					.label {
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
					.text {
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
				}
				.publisher_item {
										padding: 0 10px;
										display: none;
										.icon {
												margin: 0 2px 0 0;
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
					.label {
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
					.text {
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
				}
				.like_item {
										padding: 0 10px;
										display: none;
										.icon {
												margin: 0 2px 0 0;
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
					.label {
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
					.text {
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
				}
				.collect_item {
										padding: 0 10px;
										display: none;
										.icon {
												margin: 0 2px 0 0;
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
					.label {
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
					.text {
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
				}
				.view_item {
										padding: 0 10px;
										display: none;
										.icon {
												margin: 0 2px 0 0;
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
					.label {
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
					.text {
												color: #666;
												font-size: 12px;
												line-height: 1.5;
											}
				}
			}
		}
		.hot {
						margin: 0 0 20px;
						background: none;
						width: 100%;
						height: auto;
						order: 80;
						.hot-title {
								margin: 0;
								color: #000;
								background: none;
								width: auto;
								font-size: 24px;
								line-height: 36px;
							}
			.hot-list {
								padding: 0;
								margin: 5px 0 0;
								background: none;
								display: flex;
								width: 100%;
								border-color: #ddd;
								border-width: 1px 0 0 1px;
								justify-content: space-between;
								border-style: solid;
								flex-wrap: wrap;
								height: auto;
								.hot-item {
										cursor: pointer;
										padding: 10px;
										margin: 0;
										background: #fff;
										width: calc(25% - 0px);
										border-color: #ddd;
										border-width: 0 1px 1px 0;
										border-style: solid;
										height: auto;
										img {
												object-fit: cover;
												display: block;
												width: 110px;
												float: left;
												height: 110px;
											}
					.hot-name {
												padding: 0px 5px 0;
												overflow: hidden;
												color: #333;
												width: calc(100% - 120px);
												font-size: 15px;
												line-height: 24px;
												float: right;
												height: 72px;
											}
					.hot-time {
												padding: 0 5px;
												color: #999;
												font-weight: 500;
												display: inline-block;
												width: calc(100% - 120px);
												font-size: 14px;
												line-height: 30px;
												float: right;
												text-align: right;
											}
				}
			}
		}
		.idea1 {
						background: #fff;
						width: 100%;
						height: 1px;
						order: 10;
					}
	}
	
	.index-pv1 .animation-box {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	
	.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
	}
	
	.index-pv1 .animation-box img {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
	}
	
	.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
</style>
