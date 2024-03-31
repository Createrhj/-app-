<template>
	<view class="setting_home">
		<view class="imformation_menu_box">
			<uni-list>
				<uni-list-item showArrow title="昵称" :rightText="CurUser.userName" />
				<uni-list-item showArrow title="账号" :rightText="CurUser.userAccount" />
				<uni-list-item showArrow title="联系方式" :rightText="CurUser.userPhone" />
				<uni-list-item showArrow title="性别" :rightText="CurUser.userSex" />
				<uni-list-item showArrow title="年龄" :rightText="String(CurUser.userAge)" />
				<uni-list-item showArrow title="用户简介" :rightText="CurUser.userDetail" />
			</uni-list>
			
			<!-- 版本号 -->
			<uni-list  class="m-t-10">
				<uni-list-item showArrow title="版本" rightText="1.0.0" />
			</uni-list>
		</view>
		
		<!-- 退出登录 -->
		<view>
			<button class="m-t-11" type="default" @click='goout'>退出登录</button>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				CurUser:[],
			}
		},
		methods:{
			goout() {
				uni.navigateTo({
					url:'/pages/mine/login'
				})
				setTimeout(function() {
				  uni.showToast({
				  	title: `用户退出成功`,
				  	icon: 'none'
				  })
				}, 100)
				localStorage.removeItem("CurUser")
			},
		},
		mounted(){
			this.CurUser=JSON.parse(localStorage.getItem("CurUser"))
		}
	}
</script>

<style lang="scss">
	.setting_home{
		.imformation_menu_box{
			.m-t-10{
				margin-top: 10rpx;
			}
		}
		.m-t-11{
			margin-top: 10rpx;
				color: #ba5140;
				background-color: #fff;
			
		}
	}
</style>