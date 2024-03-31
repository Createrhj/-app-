<template>
	<view class="cart">
		<scroll-view class="content" scroll-y>	
		<view class="goodsCart" v-for="goods in cartGoods" :key="goods.goodsName">
			<view class="goodsName">
			<checkbox
			value="dd"
			style="margin-right: 0px;margin-left: 0px;"
			:checked="goods.isCheck"
			@click="checkGoods(goods)"
			></checkbox>
			{{goods.goodsStore}}
			<text style="margin-left: 140px;font-size: 13px;"@click="delGoods(goods.goodsID)">删除</text>
			</view>
			<view class="goodsInfor">
				<image :src="imgPrefix+goods.goodsImg" mode="aspectFill"></image>
				<view class="inforRight">
					<view style="display: flex; flex-direction: row;">
						<text style="font-weight: bold;font-size: 15px;">{{goods.goodsName}}</text>
					</view>
					<text style="font-weight: bold;">{{goods.goodsPrice}}元×{{goods.goodsNumber}}件</text>
						<uni-tag 
						 style="background-color: #cb523e; border-color: #cb523e; color: #fff;width: 60px;"
						 text="双十一优惠" type="primary"/>
						<view class="price">
						<text>￥{{goods.goodsTotal}}</text>
					</view>
				</view>
			</view>
		</view>
		</scroll-view>
		<view class="pay">
			<checkbox 
			value="dd"
			style="color: #FFFFFF;margin-top: 15px;margin-left: 10px;"
			:checked="isCheckAll"
			@click="checkAll"
			>全选</checkbox>
			<view class="price">
				<text style="font-weight: bold;margin-left: 5px;">合计:</text>
				<text style="font-weight: bold;font-size: 20px;">￥{{totalMoney}}</text>
			</view>
			<button class="btn" @click="toPay">结算({{number}})</button>
		</view>
	</view>
</template>

<script>
	import MarketApi from '../../api/MarketApi.js'
	export default {
		data() {
			return {
				CurUser:[],
				imgPrefix:'http://localhost:9527/app/goodsImg/',
				isCheckAll:false,
				totalMoney:0,
				number:0,
				cartGoods:[],
			}
		},
		methods: {
			selectCart(){
				MarketApi.selectCart(this.CurUser.userID).then(res=>res.data).then(res=>{
					for(const key in res.data){
						const imgList=res.data[key].goodsImg.split('/')
						res.data[key].goodsImg=imgList[0]
						if(res.data[key].isCheck==1){
							res.data[key].isCheck=true
							this.number++
							this.totalMoney=res.data[key].goodsTotal+this.totalMoney
						}else{
							res.data[key].isCheck=false
						}
					}
					this.cartGoods=res.data
				})
			},
			checkGoods(goods){
				if(goods.isCheck==false){
					MarketApi.checkSum(this.CurUser.userID,goods.goodsID,1).then(res=>res.data).then(res=>{
						this.totalMoney=res.data
						this.number++
					})
				}else if(goods.isCheck==true){
					MarketApi.checkSum(this.CurUser.userID,goods.goodsID,0).then(res=>res.data).then(res=>{
						this.totalMoney=res.data
						this.number--
					})
				}
				goods.isCheck=!goods.isCheck
			},
			checkAll(){
				this.isCheckAll=!this.isCheckAll
				if(this.isCheckAll==true){
					for(const key in this.cartGoods){
						this.cartGoods[key].isCheck=true
						MarketApi.checkSum(this.CurUser.userID,this.cartGoods[key].goodsID,1).then(res=>res.data).then(res=>{
							this.totalMoney=res.data
						})
					}
					this.number=this.cartGoods.length
				}else{
					for(const key in this.cartGoods){
						this.cartGoods[key].isCheck=false
						MarketApi.checkSum(this.CurUser.userID,this.cartGoods[key].goodsID,0)
					}
					this.totalMoney=0
					this.number=0
				}
			},
			delGoods(goodsID){
				MarketApi.deleteCart(this.CurUser.userID,goodsID).then(res=>res.data).then(res=>{
					uni.showToast({
						title: `删除商品成功`,
						icon: 'none'
					})
					this.selectCart()
				})
			},
			toPay(){
				MarketApi.defaultAddress(this.CurUser.userID).then(res=>res.data).then(res=>{
					uni.navigateTo({
						url: '/pages/payPage/payPage?addressID='+res.data.addressID
					})
				})
			}
		},
		mounted(){
			this.CurUser=JSON.parse(localStorage.getItem("CurUser"))
			this.selectCart()
		}
	}
</script>

<style lang="scss" scoped>
/deep/ .uni-checkbox-input {
	width: 20px;
	height: 20px;
	border: 1px solid #cb523e;
    border-radius: 50%;
}
/deep/ uni-checkbox .uni-checkbox-input.uni-checkbox-input-checked:before {
		border: 1px solid #cb523e;
        transform: translate(-50%,-48%) scale(1);
        -webkit-transform: translate(-50%,-48%) scale(1);
    }

/deep/ .uni-checkbox-input-checked::before{
	width: 20px;
	height: 20px;
    color: #cb523e;
	border: 1px solid #cb523e;
    border-radius: 50%;
}
.cart{
	display: flex;
	flex-direction: column;
	height: 94vh;
}
.content {
	flex: 1;
	overflow: auto;
}	
.goodsCart{
	width: 710rpx;
	height: 350rpx;
	background-color: #f5f2e9;
	border: 1px solid #d4d3ca;
	border-radius: 15px;
	display: flex;
	flex-direction: column;
	margin-bottom: 10px;
	margin-left: 10px;
	.goodsName{
		display: flex;
		font-size: 18px;
		font-weight: bold;
		margin-left: 15px;
		margin-top: 10px;
	}
	.goodsInfor{
		display: flex;
		flex-direction: row;
		margin-top: 10px;
		margin-left: 15px;
		.inforRight{
			display: flex;
			flex-direction: column;
			margin-left: 15px;
			width: 300rpx;
			text{
				margin-bottom: 5px;
			}
			.price{
				display: flex;
				flex-direction: row;
				text{
					font-weight: bold;
					color: #b93a26;
					font-size: 20px;
					margin-right: 50px;
				}
			}
		}
	}
	image{
		width: 200rpx;
		height: 200rpx;
		border-radius: 10px;

	}
}

.pay{
	width: 100%;
	height: 120rpx;
	background-color: #95302e;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	text{
		color: #FFFFFF;
	}
	.price{
		margin-top: 20px;
		margin-left: 160rpx;
	}
	.btn{
		width: 200rpx;
		height: 80rpx;
		font-size: 15px;
		border-radius: 20px;
		margin-top: 10px;
		margin-right: 10px;
	}
}
</style>
