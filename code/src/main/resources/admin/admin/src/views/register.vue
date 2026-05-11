<template>
	<div>
		<div class="register-container">
			<el-form v-if="pageFlag=='register'" ref="ruleForm" class="rgs-form animate__animated animate__backInDown" :model="ruleForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">房屋出售系统注册</div>
					<el-form-item class="list-item" v-if="tableName=='user'">
						<div class="lable" :class="changeRules('account')?'required':''">账号：</div>
						<el-input  v-model="ruleForm.account"  autocomplete="off" placeholder="账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'">
						<div class="lable" :class="changeRules('password')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.password"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'">
						<div class="lable" :class="changeRules('password')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.password2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'">
						<div class="lable" :class="changeRules('fullName')?'required':''">姓名：</div>
						<el-input  v-model="ruleForm.fullName"  autocomplete="off" placeholder="姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'">
						<div class="lable" :class="changeRules('gender')?'required':''">性别：</div>
						<el-select v-model="ruleForm.gender" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in userGenderOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'">
						<div class="lable" :class="changeRules('phone')?'required':''">手机：</div>
						<el-input  v-model="ruleForm.phone"  autocomplete="off" placeholder="手机"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'">
						<div class="lable" :class="changeRules('idCard')?'required':''">身份证：</div>
						<el-input  v-model="ruleForm.idCard"  autocomplete="off" placeholder="身份证"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='user'">
						<div class="lable" :class="changeRules('avatar')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.avatar?ruleForm.avatar:''"
							@change="avatarUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'">
						<div class="lable" :class="changeRules('developerCode')?'required':''">开发商号：</div>
						<el-input  v-model="ruleForm.developerCode"  autocomplete="off" placeholder="开发商号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'">
						<div class="lable" :class="changeRules('password')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.password"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'">
						<div class="lable" :class="changeRules('password')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.password2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'">
						<div class="lable" :class="changeRules('developerName')?'required':''">开发商名：</div>
						<el-input  v-model="ruleForm.developerName"  autocomplete="off" placeholder="开发商名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'">
						<div class="lable" :class="changeRules('gender')?'required':''">性别：</div>
						<el-select v-model="ruleForm.gender" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in developerGenderOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'">
						<div class="lable" :class="changeRules('contactPhone')?'required':''">联系电话：</div>
						<el-input  v-model="ruleForm.contactPhone"  autocomplete="off" placeholder="联系电话"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='developer'">
						<div class="lable" :class="changeRules('avatar')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.avatar?ruleForm.avatar:''"
							@change="avatarUploadChange"
						></file-upload>
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="login()">注册</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()">已有账号，直接登录</div>
						</div>
					</div>
				</div>
				<div class="idea-box1">注册</div>
				<div class="idea-box2">输入您的账号和密码以注册帐户</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import 'animate.css'
export default {
	data() {
		return {
			ruleForm: {
			},
			forgetForm: {},
            pageFlag : '',
			tableName:"",
			rules: {},
            userGenderOptions: [],
            developerGenderOptions: [],
		};
	},
	mounted(){
		this.pageFlag = this.$route.query.pageFlag
		if(this.$route.query.pageFlag=='register'){
			
			let table = this.$storage.get("loginTable");
			this.tableName = table;
			if(this.tableName=='user'){
				this.ruleForm = {
					account: '',
					password: '',
					fullName: '',
					gender: '',
					phone: '',
					idCard: '',
					avatar: '',
				}
			}
			if(this.tableName=='developer'){
				this.ruleForm = {
					developerCode: '',
					password: '',
					developerName: '',
					gender: '',
					contactPhone: '',
					avatar: '',
				}
			}
			if ('user' == this.tableName) {
				this.rules.account = [{ required: true, message: '请输入账号', trigger: 'blur' }]
			}
			if ('user' == this.tableName) {
				this.rules.password = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('developer' == this.tableName) {
				this.rules.developerCode = [{ required: true, message: '请输入开发商号', trigger: 'blur' }]
			}
			if ('developer' == this.tableName) {
				this.rules.password = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('developer' == this.tableName) {
				this.rules.developerName = [{ required: true, message: '请输入开发商名', trigger: 'blur' }]
			}
			if ('developer' == this.tableName) {
				this.rules.gender = [{ required: true, message: '请输入性别', trigger: 'blur' }]
			}
			if ('developer' == this.tableName) {
				this.rules.contactPhone = [{ required: true, message: '请输入联系电话', trigger: 'blur' }]
			}
			this.userGenderOptions = "男,女".split(',')
			this.developerGenderOptions = "男,女".split(',')
		}
	},
	created() {
	},
	destroyed() {
		  	},
	methods: {
		changeRules(name){
			if(this.rules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		close(){
			this.$router.push({ path: "/login" });
		},
        avatarUploadChange(fileUrls) {
            this.ruleForm.avatar = fileUrls;
        },
        avatarUploadChange(fileUrls) {
            this.ruleForm.avatar = fileUrls;
        },

        // 多级联动参数


		// 注册
		login() {
			var url=this.tableName+"/register";
			if(this.tableName == 'user'){
				if(!this.ruleForm.account){
					this.$message.error(`账号不能为空`);
					return
				}
				if(!this.ruleForm.password){
					this.$message.error(`密码不能为空`);
					return
				}
				if(this.ruleForm.password!=this.ruleForm.password2){
					this.$message.error(`两次密码输入不一致`);
					return
				}
				if(this.ruleForm.phone &&(!this.$validate.isMobile(this.ruleForm.phone))){
					this.$message.error(`手机应输入手机格式`);
					return
				}
				if(this.ruleForm.idCard &&(!this.$validate.checkIdCard(this.ruleForm.idCard))){
					this.$message.error(`身份证应输入身份证格式`);
					return
				}
			}
			if(this.tableName == 'developer'){
				if(!this.ruleForm.developerCode){
					this.$message.error(`开发商号不能为空`);
					return
				}
				if(!this.ruleForm.password){
					this.$message.error(`密码不能为空`);
					return
				}
				if(this.ruleForm.password!=this.ruleForm.password2){
					this.$message.error(`两次密码输入不一致`);
					return
				}
				if(!this.ruleForm.developerName){
					this.$message.error(`开发商名不能为空`);
					return
				}
				if(!this.ruleForm.gender){
					this.$message.error(`性别不能为空`);
					return
				}
				if(!this.ruleForm.contactPhone){
					this.$message.error(`联系电话不能为空`);
					return
				}
				if(this.ruleForm.contactPhone &&(!this.$validate.isMobile(this.ruleForm.contactPhone))){
					this.$message.error(`联系电话应输入手机格式`);
					return
				}
			}
            if(this.ruleForm.avatar!=null) {
                this.ruleForm.avatar = this.ruleForm.avatar.replace(new RegExp(this.$base.url,"g"),"");
            }
			const submitData = Object.assign({}, this.ruleForm);
			delete submitData.password2;
			this.$http({
				url: url,
				method: "post",
				data:submitData
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "注册成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.$router.replace({ path: "/login" });
						}
					});
				} else {
					this.$message.error(data.msg);
				}
			}).catch(() => {
				this.$message.error('请求失败，请确认后端服务已启动并且接口地址正确');
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.register-container {
	position: relative;
	background: url(http://codegen.caihongy.cn/20240920/c1dd29229033480faa17cc36709463ef.png);
	background-repeat: no-repeat;
	background-size: cover !important;
	background: url(http://codegen.caihongy.cn/20240920/c1dd29229033480faa17cc36709463ef.png);
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: flex-end;
	align-items: center;
	position: relative;
	.rgs-form {
		.rgs-form2 {
		flex-direction: column;
		display: flex;
		width: 100%;
		flex-wrap: wrap;
		}
		padding: 20px 40px 40px 40px;
		margin: 0 351px 0 0;
		z-index: 1000;
		background-size: cover;
		display: flex;
		min-height: 100%;
		flex-wrap: wrap;
		border-radius: 0;
		box-shadow: inset 0px 0px 0px 0px #000;
		flex-direction: column;
		width: 600px;
		align-items: flex-start;
		position: relative;
		height: auto;
		.title {
			padding: 0;
			margin: 0 0% 20px 0;
			color: #000000;
			top: 60px;
			left: 0;
			background: none;
			font-weight: 400;
			width: 100%;
			font-size: 28px;
			line-height: 40px;
			text-align: left;
		}
		.list-item {
			padding: 0 ;
			margin: 0 0 15px  80px;
			width: 84%;
			position: relative;
			height: auto;
			::v-deep  .el-form-item__content {
				display: block;
			}
			.lable {
				color: #000;
				left: -78px;
				letter-spacing: 1px;
				width: 120px;
				font-size: 18px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				position: absolute!important;
				border-style: solid;
				text-align: left;
				height: 46px;
			}
			.el-input {
				border-radius: 0;
				flex: 1;
				width: 100%;
			}
			.el-input ::v-deep  .el-input__inner {
				border-radius: 0;
				padding: 0 10px;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 18px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			.el-input ::v-deep  .el-input__inner:focus {
				border-radius: 0;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 18px;
				border-color: #D8D8D8;
				border-width: 0 0 2px 0;
				border-style: solid;
				height: 46px;
			}
			.el-input-number {
				border-radius: 0;
				flex: 1;
				width: 100%;
			}
			.el-input-number ::v-deep  .el-input__inner {
				text-align: center;
				border-radius: 0;
				padding: 0 10px;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 18px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			.el-input-number ::v-deep  .el-input__inner:focus {
				border-radius: 0;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 18px;
				border-color: #D8D8D8;
				border-width: 0 0 2px 0;
				border-style: solid;
				height: 46px;
			}
			.el-input-number ::v-deep  .el-input-number__decrease {
				display: none;
			}
			.el-input-number ::v-deep  .el-input-number__increase {
				display: none;
			}
			.el-select {
				width: 100%;
			}
			.el-select ::v-deep  .el-input__inner {
				border-radius: 0;
				padding: 0 10px;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 18px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			.el-select ::v-deep  .el-input__inner:focus {
				border-radius: 0;
				padding: 0 10px;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 18px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			.el-date-editor {
				width: 100%;
			}
			.el-date-editor ::v-deep  .el-input__inner {
				border-radius: 0;
				padding: 0 30px;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 18px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			.el-date-editor ::v-deep  .el-input__inner:focus {
				border-radius: 0;
				padding: 0 10px;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 18px;
				border-color: #000;
				border-width: 0 0 2px 0;
				border-style: solid;
				height: 46px;
			}
			.el-date-editor.el-input {
				width: 100%;
			}
			::v-deep  .el-upload--picture-card {
				background: transparent;
				border: 0;
				border-radius: 0;
				width: auto;
				height: auto;
				line-height: initial;
				vertical-align: middle;
			}
			::v-deep  .upload .upload-img {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				margin: 0 0 0 45px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			::v-deep  .el-upload-list .el-upload-list__item {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				margin: 0 0 0 45px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			::v-deep  .el-upload .el-icon-plus {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				margin: 0 0 0 45px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			::v-deep  .el-upload__tip {
				margin: 0 0 0 45px;
				color: #666;
				font-size: 15px;
			}
			::v-deep  .el-input__inner::placeholder {
				color: #999;
				font-size: 18px;
			}
			.required {
				position: relative;
			}
			.required::after{
				color: red;
				left: -10px;
				position: inherit;
				content: "*";
				order: -1;
			}
			.editor {
				margin: 0 0 0 40px;
				background: #fff;
				width: 92%;
				height: auto;
			}
			.editor>.avatar-uploader {
				line-height: 0;
				height: 0;
			}
		}
		.list-item.email {
			input {
				border-radius: 0;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 16px;
				border-color:  #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			input:focus {
				border-radius: 0;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 16px;
				border-color:  #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			input::placeholder {
				color: #999;
				font-size: 18px;
			}
			button {
				border: 0px solid #efeff7;
				cursor: pointer;
				border-radius: 0 4px 4px 0;
				padding: 0;
				margin: 1px 0 0;
				color: #fff;
				background: #FFC144;
				width: 100px;
				font-size: 15px;
				position: absolute;
				right: 0px;
				height: 38px;
			}
			button:hover {
				opacity: 0.8;
			}
		}
		.register-btn {
			margin: 20px auto;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			order: 2;
		}
		.register-btn1 {
			order: 3;
		}
		.register-btn2 {
			width: 100%;
		}
		.r-btn {
			border: 0px solid rgba(0, 0, 0, 1);
			cursor: pointer;
			padding: 0 10px;
			margin: 0 0 10px  ;
			color: #fff;
			font-weight: 500;
			letter-spacing: 1px ;
			font-size: 36px;
			border-radius: 10px;
			background: #FFC144;
			width: 520px;
			min-width: 68px;
			height: 80px;
		}
		.r-btn:hover {
			border: 0px solid rgba(0, 0, 0, 1);
			opacity: 0.8;
		}
		.r-login {
			cursor: pointer;
			padding: 0;
			color: #666;
			display: inline-block;
			text-decoration: underline;
			width: 100%;
			font-size: 18px;
			line-height: 40px;
			text-align: right;
		}
		.r-login:hover {
			opacity: 1;
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
	
	::-webkit-scrollbar {
	  display: none;
	}
</style>
