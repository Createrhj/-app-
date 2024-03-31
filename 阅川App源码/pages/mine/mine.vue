<template>
<view class="user_home">
		<!-- 头像区域 -->
		<view class="top_bj">
					<view class="user_scp">
						<image v-if="this.CurUser!=null" :src="imgPrefix+this.CurUser.userAvatar"></image>
						<image v-if="this.CurUser==null" src="../../static/hj/user_scp.png"></image>
					</view>
					<view class="user_name" v-if="this.CurUser!=null" >
						<text style="margin-bottom: 5px;">{{this.CurUser.userName}}</text>
						<text style="font-size: 10px;">账号:{{this.CurUser.userAccount}}</text>
					</view>
					<view @click="toLogin()" class="user_name">
						<text  v-if="this.CurUser==null" style="font-weight: bold;margin-left: 20px;">点击登录</text>
					</view>
		</view>
		<!-- 用户菜单区域 -->
		<view class="user_menu_box">
			<uni-list>
				<uni-list-item clickable @click="toShoppingCart()" :show-extra-icon="true" showArrow :extra-icon="Icon1" title="购物车" />
				<uni-list-item clickable @click="toAddressPage()"  :show-extra-icon="true" showArrow :extra-icon="Icon5" title="地址管理" />
				<uni-list-item clickable @click="toOrders()" :show-extra-icon="true" showArrow :extra-icon="Icon6" title="我的订单" />
				<uni-list-item clickable @click="toCollection()"  :show-extra-icon="true" showArrow :extra-icon="Icon2" title="我的收藏" />
				<uni-list-item clickable @click="toSetting()"  :show-extra-icon="true" showArrow :extra-icon="Icon3" title="账户管理" />
				<uni-list-item clickable @click="toHelp()"  :show-extra-icon="true" showArrow :extra-icon="Icon4" title="帮助与反馈" />
			</uni-list>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				CurUser:[],
				userName:[],
				imgPrefix:'http://localhost:9527/app/userAvatar/',
				Icon1:{
					color:'#ba5140',
					size:'22',
					type:'cart-filled'
				},
				Icon2:{
					color:'#ba5140',
					size:'22',
					type:'heart-filled'
				},
				Icon3:{
					color:'#ba5140',
					size:'22',
					type:'person-filled'
				},
				Icon4:{
					color:'#ba5140',
					size:'22',
					type:'help-filled'
				},
				Icon5:{
					color:'#ba5140',
					size:'22',
					type:'email-filled'
				},
				Icon6:{
					color:'#ba5140',
					size:'22',
					type:'calendar-filled'
				},
			}
		},
		methods: {
			toLogin(){
				uni.navigateTo({
					url: '/pages/mine/login'
				})
			},
			toShoppingCart(){
				if(this.CurUser!=null){
					uni.navigateTo({
						url:'/pages/shoppingCart/shoppingCart'
					})
				}else{
					uni.showToast({
						title: `用户请先登录`,
						icon: 'none'
					})
				}
			},
			toAddressPage(){
				if(this.CurUser!=null){
					uni.navigateTo({
						url:'/pages/addressPage/addressPage'
					})
				}else{
					uni.showToast({
						title: `用户请先登录`,
						icon: 'none'
					})
				}
			},
			toOrders(){
				if(this.CurUser!=null){
					uni.navigateTo({
						url:'/pages/order/order'
					})
				}else{
					uni.showToast({
						title: `用户请先登录`,
						icon: 'none'
					})
				}
			},
			toCollection(){
				if(this.CurUser!=null){
					uni.navigateTo({
						url:'/pages/mine/collect'
					})
				}else{
					uni.showToast({
						title: `用户请先登录`,
						icon: 'none'
					})
				}
			},
			toSetting(){
				if(this.CurUser!=null){
					uni.navigateTo({
						url:'/pages/mine/setting'
					})
				}else{
					uni.showToast({
						title: `用户请先登录`,
						icon: 'none'
					})
				}
			},
			toHelp(){
				if(this.CurUser!=null){
					uni.navigateTo({
						url:'/pages/help/help'
					})
				}else{
					uni.showToast({
						title: `用户请先登录`,
						icon: 'none'
					})
				}
			},
			getUser(){
				this.CurUser=JSON.parse(localStorage.getItem("CurUser"))
				console.log(this.CurUser)
			}
		},
		mounted() {
			this.getUser()
		},
	}
</script>

<style lang="scss">
	.user_home{
		.top_bj{
			display: flex;
			width: 100%;
			height: 450rpx;
			background-image: url('../../static/bg9.jpg');
			background-repeat:no-repeat;
			background-size:100%;
			border-radius: 0 0 25px 25px;
			box-shadow: 0 0 5px 1px rgba($color:#000000, $alpha:0.1);  //alpha是0-1的透明度
			align-items: center;
			.user_scp{
				flex-shrink: 0;
				width: 180rpx;
				height: 180rpx;
				border-radius: 50%;
				background-color: #878787;
				margin-left: 40px;
				margin-right: 10px;
				image{
					width: 100%;
					height: 100%;
					border-radius: 100%;
				}
			}
			.user_name{
				font-size: 20px;
				box-sizing: border-box;
				white-space: nowrap;//防止长文本超出
				text-overflow:ellipsis;//超出部分用点点点代替
				overflow: hidden;
				color: #FFFFFF;
				display: flex;
				flex-direction: column;
			}
		}
		.user_menu_box{
			background-color: #fff;
			margin: 10px 10px;
		}
	}

</style>
