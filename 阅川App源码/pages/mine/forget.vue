<template>
	<view class="forget_home">
		<!-- 忘记密码表单校验 -->
			<view class="forget_box">
				<view class="forget_tip">我们将发送邮件，请在邮件中修改密码</view>
				<uni-forms ref="forgetRef" :rules="rules" :model="forgetForm">
					<uni-forms-item name="email">
						<uni-easyinput v-model="forgetForm.email" placeholder="请输入邮箱" />
					</uni-forms-item>
				</uni-forms>
				<button class="button" @click="btpForfet">发送邮箱</button>
			</view>
			<!-- 返回登录界面 -->
			<view class="login-section">
				<text @click="tologin">返回登录界面</text>
			</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				forgetForm:{
					email:""
				},
				//忘记密码表单的校验规则
				rules: {
					email: {
						rules: [{
							required: true,
							errorMessage: '邮箱不能为空'
						}]
					}
				},
			}
	
		},
		methods:{
			btpForfet(){
				//校验请求参数
				this.$refs.forgetRef.validate().then(res=>{
					//调用登录的方法
					console.log('表单错误信息：');
				}).catch(err =>{
					console.log('表单错误信息：', err);
				})
			},
			//返回登录界面
			tologin(){
				uni.navigateTo({
					url:'/pages/mine/login'
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	/deep/ .uni-forms-item__error[data-v-61dfc0d0] {
		color: #ebeddf !important;
	}
	.forget_home{
		width: 100vw;
		height: 100vh;
		box-sizing: border-box;
		background-image: url("../../static/bg6.jpg");
		background-repeat: no-repeat;
		background-size: 100% 100%;
		.forget_box{
			position:relative;
			top:500rpx ;
			margin: 0 40px;
			.forget_tip{
				color: #FFFFFF;
				font-size: 30;
			}
			.button {
			  width: 100%;
			  height: 45px;
			  display: inline-block;
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
			}

			.button:hover {background-color: #ddbb99}

			.button:active {
			  background-color:#ddbb99;;
			  transform: translateY(4px);
			}
		}
		.login-section{
			position: absolute;
			bottom: 50rpx;
			width: 100%;
			text-align: center;
			text{
				color: #FFFFFF;;
			}
		}
	}
</style>