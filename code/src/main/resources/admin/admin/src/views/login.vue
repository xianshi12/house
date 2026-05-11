<template>
	<div>
		<div class="login-container">
			<el-form class="login_form animate__animated animate__backInDown">
				<div class="login_form2">
					<div class="title-container">房屋出售系统</div>
					<div v-if="loginType==1" class="list-item">
						<div class="lable">
							账号
						</div>
						<input placeholder="请输入账号" name="username" type="text" v-model="rulesForm.username">
					</div>
					<div v-if="loginType==1" class="list-item">
						<div class="lable">
							密码
						</div>
						<div class="password-box">
							<input placeholder="请输入密码" name="password" :type="showPassword?'text':'password'" v-model="rulesForm.password">
							<span class="icon iconfont" :class="showPassword?'icon-liulan13':'icon-liulan17'" @click="showPassword=!showPassword"></span>
						</div>
					</div>

					<div class="list-item " v-if="roles.length>1">
						<div class="lable">
							角色
						</div>
						<div prop="loginInRole" class="list-type">
							<el-radio v-if="loginType==1||(loginType==2&&item.roleName!='管理员')" v-for="item in roles" v-bind:key="item.roleName" v-model="rulesForm.role" :label="item.roleName">{{item.roleName}}</el-radio>
						</div>
					</div>

		
					<div class="login-btn">
						<div class="login-btn1">
							<el-button v-if="loginType==1" type="primary" @click="login()" class="loginInBt">登录</el-button>
						</div>
						<div class="login-btn2">
							<el-button type="primary" @click="register('developer')" class="register">
								注册开发商							</el-button>
						</div>
						<div class="login-btn3">
						</div>
					</div>
				</div>
				<div class="idea-box1">登录</div>
				<div class="idea-box2">输入您的账号和密码以访问帐户</div>
			</el-form>
		</div>
	</div>
</template>
<script>
	import 'animate.css'
	import menu from "@/utils/menu";
	export default {
		data() {
			return {
				verifyCheck2: false,
				flag: false,
				baseUrl:this.$base.url,
				loginType: 1,
				rulesForm: {
					username: "",
					password: "",
					role: "",
				},
				menus: [],
				roles: [],
				tableName: "",
				showPassword: false,
			};
		},
		mounted() {
			let menus = menu.list();
			this.menus = menus;

			for (let i = 0; i < this.menus.length; i++) {
				if (this.menus[i].hasBackLogin=='是') {
					this.roles.push(this.menus[i])
				}
			}

		},
		created() {

		},
		destroyed() {
		},
		components: {
		},
		methods: {

			//注册
			register(tableName){
				this.$storage.set("loginTable", tableName);
				this.$router.push({path:'/register',query:{pageFlag:'register'}})
			},
			// 登陆
			login() {

				if (!this.rulesForm.username) {
					this.$message.error("请输入用户名");
					return;
				}
				if (!this.rulesForm.password) {
					this.$message.error("请输入密码");
					return;
				}
				if(this.roles.length>1) {
					if (!this.rulesForm.role) {
						this.$message.error("请选择角色");
						return;
					}

					let menus = this.menus;
					for (let i = 0; i < menus.length; i++) {
						if (menus[i].roleName == this.rulesForm.role) {
							this.tableName = menus[i].tableName;
						}
					}
				} else {
					this.tableName = this.roles[0].tableName;
					this.rulesForm.role = this.roles[0].roleName;
				}
		
				this.loginPost()
			},
			loginPost() {
				this.$http({
					url: `${this.tableName}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
					method: "post"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.$storage.set("Token", data.token);
						this.$storage.set("role", this.rulesForm.role);
						this.$storage.set("sessionTable", this.tableName);
						this.$storage.set("adminName", this.rulesForm.username);
						this.$nextTick(()=>{
							this.$http({
								url: this.tableName + '/session',
								method: "get"
							}).then(({
								data
							}) => {
								if (data && data.code === 0) {
									if(this.tableName == 'user') {
										this.$storage.set('headportrait',data.data.avatar)
									}
									if(this.tableName == 'developer') {
										this.$storage.set('headportrait',data.data.avatar)
									}
									if(this.tableName == 'users') {
										this.$storage.set('headportrait',data.data.avatar)
									}
									this.$storage.set('userForm',JSON.stringify(data.data))
									this.$storage.set('userid',data.data.id);
								} else {
									let message = this.$message
									message.error(data.msg);
								}
								this.$router.replace({ path: "/" });
							});
						})
					} else {
						this.$message.error(data.msg);
					}
				}).catch(() => {
					this.$message.error('请求失败，请确认后端服务已启动并且接口地址正确');
				});
			},
		}
	}
</script>

<style lang="scss" scoped>
.login-container {
	min-height: 100vh;
	position: relative;
	background-repeat: no-repeat;
	background-position: center center;
	background-size: cover;
	background: url(http://codegen.caihongy.cn/20240920/c1dd29229033480faa17cc36709463ef.png);
	background-repeat: no-repeat;
	background-size: cover !important;
	background: url(http://codegen.caihongy.cn/20240920/c1dd29229033480faa17cc36709463ef.png);
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: flex-end;
	align-items: center;
	background-position: center center;

	.login_form {
		padding: 20px 0px 40px;
		margin: 0 351px 0 0;
		z-index: 1000;
		background-size: cover;
		display: flex;
		min-height: 100%;
		flex-wrap: wrap;
		border-radius: 0;
		box-shadow: inset 0px 0px 0px 0px #000;
		flex-direction: column;
		width: 660px;
		align-items: flex-start;
		position: relative;
		height: auto;
		.login_form2 {
			flex-direction: column;
			display: flex;
			width: 100%;
		}
		.title-container {
			margin: 0 0 20px 0;
			color: #000000;
			top: 60px;
			left: 0;
			background: none;
			font-weight: 400;
			width: 100%;
			font-size: 26px;
			line-height: 40px;
			text-align: left;
		}
		.list-item {
			padding: 0;
			margin: 0 0 10px;
			display: flex;
			width: 100%;
			position: relative;
			align-items: center;
			.lable {
				color: #000;
				width: 80px;
				font-size: 18px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				line-height: 40px;
				border-style: solid;
				text-align: left;
			}
			input {
				border-radius: 0;
				padding: 0 10px;
				outline: none;
				color: #666;
				width: 100%;
				font-size: 18px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 40px;
			}
			input:focus {
				padding: 0 10px;
				outline: none;
				color: #666;
				width: 100%;
				font-size: 18px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 40px;
			}
			.password-box {
				display: flex;
				width: 100%;
				position: relative;
				align-items: center;
				input {
					padding: 0 10px;
					color: rgba(64, 158, 255, 1);
					width: 100%;
					font-size: 18px;
					border-color: #D8D8D8;
					border-width: 0 0 1px 0;
					border-style: solid;
					height: 41px;
				}
				input:focus {
					padding: 0 10px;
					outline: none;
					color: #666;
					width: 100%;
					font-size: 18px;
					border-color: #D8D8D8;
					border-width: 0 0 1px 0;
					border-style: solid;
					height: 40px;
				}
				.iconfont {
					cursor: pointer;
					z-index: 1;
					color: #000;
					top: 0;
					font-size: 16px;
					line-height: 44px;
					position: absolute;
					right: 5px;
				}
			}
			input::placeholder {
				color: #999;
				font-size: 18px;
			}
		}
		.list-type {
			padding: 0;
			margin: 0;
			background: none;
			width: calc(100% - 0px);
			border-color: #D8D8D8;
			border-width: 0 0 1px 0;
			line-height: 40px;
			border-style: solid;
			height: auto;
			::v-deep  .el-radio__input .el-radio__inner {
				background: rgba(53, 53, 53, 0);
				border-color: #666;
			}
			::v-deep  .el-radio__input.is-checked .el-radio__inner {
				background: #FFC144;
				border-color: #FFC144;
			}
			::v-deep  .el-radio__label {
				color: #000000;
				font-size: 18px;
			}
			::v-deep  .el-radio__input.is-checked+.el-radio__label {
				color: #FFC144;
				font-size: 18px;
			}
		}
		.login-btn {
			margin: 20px auto;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			order: 2;
			.login-btn1 {
				width: 100%;
				order: 3;
			}
			.login-btn2 {
				padding: 0 0px;
				margin: 30px 0 0 0;
				display: flex;
				width: 100%;
				justify-content: center;
				align-items: center;
				flex-wrap: wrap;
				order: 4;
			}
			.login-btn3 {
				width: 100%;
				order: 2;
			}
			.loginInBt {
				border: 0px solid rgba(0, 0, 0, 1);
				cursor: pointer;
				padding: 0 10px;
				margin: 0 0 10px;
				color: #fff;
				font-weight: 500;
				letter-spacing: 1px ;
				font-size: 30px;
				border-radius: 10px;
				background: #FFC144;
				width: 100%;
				min-width: 68px;
				height: 60px;
			}
			.loginInBt:hover {
				opacity: 0.8;
			}
			.register {
				border: 2px solid #000000;
				cursor: pointer;
				border-radius: 10px;
				padding: 0 10px;
				margin: 0 5px 20px;
				color: rgba(0, 0, 0, 1);
				background: #fff;
				width: auto;
				font-size: 18px;
				height: 40px;
			}
			.register:hover {
				opacity: 0.8;
			}
			.forget {
				border: 0;
				cursor: pointer;
				border-radius: 0;
				padding: 0;
				margin: 0 10px 20px 0;
				color: #000000;
				background: none;
				font-weight: 400;
				width: 100%;
				font-size: 20px;
				text-align: right;
				height: 34px;
			}
			.forget:hover {
				color: #0d6efd;
				opacity: 1;
			}
		}
	}
	.idea-box1 {
		background: none;
		font-weight: 600;
		display: none;
		width: 560px;
		font-size: 20px;
		height: 30px;
		order: -2;
	}
	.idea-box2 {
		margin: 5px 0 40px;
		background: #fff;
		display: none;
		width: 100%;
		font-size: 16px;
		height: 30px;
		order: -1;
	}
}

</style>
