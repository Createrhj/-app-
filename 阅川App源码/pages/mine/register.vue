<template>
	<view class="register_home">
		<!-- register字样 -->
		<view class="register_title">REGISTER</view>
		<!-- 注册表单校验 -->
			<view class="register_box">
				<uni-forms ref="regRef" :rules="rules" :model="regForm">
					<uni-forms-item name="userAccount">
						<uni-easyinput v-model="regForm.userAccount" placeholder="请输入账号" />
					</uni-forms-item>
					<uni-forms-item name="userPhone">
						<uni-easyinput  v-model="regForm.userPhone" placeholder="请输入电话" />
					</uni-forms-item>
					<uni-forms-item  name="userPassword">
						<uni-easyinput type="password" v-model="regForm.userPassword" placeholder="请输入密码" />
					</uni-forms-item>
					<uni-forms-item name="checkPassword">
						<uni-easyinput type="password" v-model="regForm.checkPassword" placeholder="再次确认密码" />
					</uni-forms-item>
				</uni-forms>
				<button class="button" @click="btpRegister">注册</button>
			</view>
		<!-- 已有账号？登录 -->
		<view class="login-section">
			<text style="color: #ffffff;margin-right: 5px;">已有账号</text>
			<text @click="toLogin">登录</text>
		</view>
	</view>
</template>

<script>
	import LoginApi from '../../api/LoginApi.js'
	
	export default{
		data(){
			return{
				//注册输入的表单
				regForm:{
					userAccount:"",
					userPassword:"",
					userPhone:"",
					checkPassword:"",
				},
				//注册表单的校验规则
				rules: {
					userAccount: {
						rules: [{
							required: true,
							errorMessage: '昵称不能为空'
						}]
					},
					userPhone: {
						rules: [{
							required: true,
							errorMessage: '邮箱不能为空'
						}]
					},
					userPassword: {
						rules: [{
							required: true,
							errorMessage: '密码不能为空'
						}]
					},
					checkPassword: {
						rules: [{
							required: true,
							errorMessage: '再次输入密码'
						}]
					}
				},
			}
	
		},
		methods:{
			
			//点击注册
			btpRegister(){
				//校验请求参数
				this.$refs.regRef.validate().then(res=>{
					this.userRegister()
				}).catch(err =>{
					console.log('表单错误信息：', err);
				})
			},
			userRegister(){
				LoginApi.userRegister(this.regForm).then(res=>res.data).then(res=>{
					if(res.code===200){
						uni.navigateTo({
							url:'/pages/mine/login'
						})
						setTimeout(function() {
						uni.showToast({
							title: `注册成功`,
							icon: 'none'
						})
						}, 100)
					}else{
						uni.showToast({
							title: `注册失败`,
							icon: 'none'
						})
					}
				})
			},
			toLogin(){
				uni.navigateTo({
					url:'/pages/mine/login'
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	
	/deep/ .uni-easyinput__content-input[data-v-abe12412] {
		background:rgba(255,255,255,0.15) !important;
		border-radius: 15px;
	}
	/deep/ .uni-forms-item__error[data-v-61dfc0d0] {
		color: #ebeddf !important;
	}
	
	.register_home{
		width: 100vw;
		height: 100vh;
		box-sizing: border-box;
		background-image: url("../../static/bg4.jpg");
		background-repeat: no-repeat;
		background-size: 100% 100%;
		.register_title{
			color: #fff;
			font-size: 140upx;
			position: relative;
			top: 250rpx;
			left: 20rpx;
			opacity: 0.7;   //字体透明度
		}
		.register_box{
			position:relative;
			top:430rpx ;
			margin: 0 40px;
			.button {
			  width: 100%;
			  height: 45px;
			  display: inline-block;
			  //padding: 5px 25px;
			  align-items: center;
			  justify-content: center;
			  cursor: pointer;
			  text-align: center;   
			  text-decoration: none;
			  outline: none;
			  color: #fff;
			  background-color:#ddbb99;
			  border: none;
			  border-radius: 46px;
			 // box-shadow: 0 9px #999;
			}

			.button:hover {background-color:#ddbb99;}

			.button:active {
			  background-color:#ddbb99;;
			 // box-shadow: 0 5px #666;
			  transform: translateY(4px);
			}
		}
		.login-section{
			position: absolute;
			bottom: 50rpx;
			width: 100%;
			text-align: center;
			text{
				color: #FFFFFF;
			}
		}
	}
</style>