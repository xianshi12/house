<template>
	<div class="storeup-page">
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>

		<div class="section-title">我的收藏</div>

		<el-tabs v-model="activeTab" class="storeup-tabs" @tab-click="tabClick">
			<el-tab-pane label="房源收藏" name="fangyuanxinxi"></el-tab-pane>
			<el-tab-pane label="售房资讯收藏" name="news"></el-tab-pane>
		</el-tabs>

		<el-form :inline="true" :model="formSearch" class="formSearch">
			<el-form-item>
				<el-input v-model="formSearch.name" placeholder="名称"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="getStoreupList(1)">查询</el-button>
			</el-form-item>
		</el-form>

		<div class="storeup-grid" v-if="storeupList.length">
			<div class="storeup-card" v-for="item in storeupList" :key="item.id" @click="toDetail(item)">
				<div class="thumb">
					<el-image v-if="imageUrl(item)" :src="imageUrl(item)" fit="cover"></el-image>
					<div v-else class="empty-thumb">暂无图片</div>
				</div>
				<div class="card-body">
					<div class="name">{{item.name}}</div>
					<div class="type">{{activeTab === 'news' ? '售房资讯' : '房源信息'}}</div>
				</div>
			</div>
		</div>

		<el-empty v-else description="暂无收藏"></el-empty>

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
			@prev-click="prevClick"
			@size-change="sizeChange"
			@next-click="nextClick"
		></el-pagination>
	</div>
</template>

<script>
	import config from '@/config/config'
	export default {
		data() {
			return {
				baseUrl: config.baseUrl,
				formSearch: {
					name: ''
				},
				activeTab: 'fangyuanxinxi',
				storeupType: 1,
				storeupList: [],
				total: 0,
				pageSize: 8,
				pageSizes: [],
				totalPage: 1
			}
		},
		created() {
			this.getStoreupList(1);
		},
		methods: {
			backClick() {
				this.$router.push('/index/center')
			},
			tabClick() {
				this.formSearch.name = '';
				this.getStoreupList(1);
			},
			getStoreupList(page) {
				if(!localStorage.getItem('frontToken') || !localStorage.getItem('frontUserid')) {
					this.$message.warning('请先登录后查看收藏');
					this.$router.push('/login');
					return;
				}
				let params = {
					page,
					limit: this.pageSize,
					type: this.storeupType,
					tablename: this.activeTab,
					userid: Number(localStorage.getItem('frontUserid')),
					sort: 'addtime',
					order: 'desc'
				};
				let searchWhere = {};
				if (this.formSearch.name != '') searchWhere.name = '%' + this.formSearch.name + '%';
				this.$http.get('storeup/list', {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.storeupList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			imageUrl(item) {
				if(!item.picture) return '';
				let picture = item.picture.split(',')[0];
				if(picture.substr(0,4) == 'http') return picture;
				return this.baseUrl + picture;
			},
			curChange(page) {
				this.getStoreupList(page);
			},
			prevClick(page) {
				this.getStoreupList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getStoreupList(1);
			},
			nextClick(page) {
				this.getStoreupList(page);
			},
			toDetail(item) {
				let path = item.tablename === 'news' ? '/index/newsDetail' : '/index/fangyuanxinxiDetail';
				this.$router.push({path, query: {id:item.refid, storeupType:1}});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.storeup-page {
		padding: 0 0 20px;
		margin: 0 auto;
		color: #333;
		background: #fff;
		width: 1200px;
		font-size: 16px;
		position: relative;
	}

	.section-title {
		padding: 0;
		margin: 20px 0;
		border-bottom: 2px solid #0063CD;
		color: #333;
		line-height: 50px;
		font-size: 26px;
	}

	.storeup-tabs {
		margin-bottom: 12px;
	}

	.formSearch {
		text-align: right;
	}

	.storeup-grid {
		display: grid;
		grid-template-columns: repeat(4, 1fr);
		gap: 20px;
	}

	.storeup-card {
		border: 1px solid #e6e6e6;
		background: #fff;
		cursor: pointer;
		transition: border-color .2s, box-shadow .2s;
	}

	.storeup-card:hover {
		border-color: #0063CD;
		box-shadow: 0 4px 14px rgba(0, 0, 0, .08);
	}

	.thumb {
		width: 100%;
		aspect-ratio: 4 / 3;
		background: #f5f7fa;
		overflow: hidden;
	}

	.thumb .el-image {
		display: block;
		width: 100%;
		height: 100%;
	}

	.empty-thumb {
		display: flex;
		align-items: center;
		justify-content: center;
		width: 100%;
		height: 100%;
		color: #999;
		background: #f5f7fa;
	}

	.card-body {
		padding: 12px;
		min-height: 78px;
	}

	.name {
		color: #333;
		font-size: 16px;
		line-height: 22px;
		height: 44px;
		overflow: hidden;
	}

	.type {
		margin-top: 8px;
		color: #888;
		font-size: 14px;
		line-height: 20px;
	}

	.pagination {
		padding: 0;
		margin: 20px auto;
		text-align: center;
		width: 100%;
	}
</style>
