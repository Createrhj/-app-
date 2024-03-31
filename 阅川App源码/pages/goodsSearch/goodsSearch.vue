<template>
	<view class="goodsSearch">
		<uni-search-bar radius="100" placeholder="请输入搜索内容" clearButton="none"/>
		<view class="goodsTotal">
			<view class="filter" style="font-size: 16px;margin-top: -15px;margin-bottom: 10px;">
				<text style="color: #631216;font-weight: bold;">综合</text>
				<text style="color: #631216;font-weight: bold;">价格</text>
				<text style="color: #631216;font-weight: bold;">销量</text>
				<text @click="openPopup" style="color: #631216;font-weight: bold;">筛选</text>
			</view>
			
			<view class="goodsList" @click="toGoodsDetail(goods.goodsID)" v-for="goods in goodsData" :key="goods.goodsID">
				<image :src="imgPrefix+goods.goodsImg" mode="aspectFill"></image>
				<view class="goodsInfor">
					<view class="inforList" style="margin-bottom: 20px;margin-top: 5px;">
						<uni-tag 
						style="background-color: #cb523e; border-color: #cb523e; color: #fff;"
						:inverted="true" :text="goods.goodsCategory" size="small" />
						<text style="font-weight: bold;">{{goods.goodsName}}</text>
					</view>
					<view class="inforList">
						<text class="price">￥{{goods.goodsPrice}}</text>
						<text>2000+人付款</text>
					</view>
					<view class="inforList">
						<uni-tag 
						style="background-color: #ee7959;border-color: #ee7959; color: #fff;"
						v-for="tag in goods.goodsDetail" :key="tag"
						:inverted="true" :text="tag" type="error" size="small"/>
					</view>
					<view class="inforList" style="margin-top: 8px;">
						<uni-tag 
						style="background-color: #b93a26;border-color:#b93a26; color: #fff;"
						:circle="true" :text="goods.goodsStore" size="mini" :inverted="true"/>
					</view>
				</view>
			</view>
			
		</view>
		
		<uni-popup ref="popup" type="top">
			<view class="filterPopup">
				<text >文创商品类型</text>
				<view class="filterType">
					<button>饮食</button>
					<button>文具</button>
					<button>娱乐</button>
				</view>
				<view class="filterType">
					<button>茶类</button>
					<button>服饰</button>
					<button>艺术品</button>
				</view>
				<text style="margin-top: 20px;">价格区间</text>
				<view class="filterType">
					<input  placeholder="自定义最低价"/>
					<uni-icons type="arrow-right" size="20"></uni-icons>
					<input placeholder="自定义最高价"/>
				</view>
				<text>发货地</text>
				<view class="filterType">
					<button>成都</button>
					<button>广东</button>
					<button>上海</button>
				</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
import MarketApi from '../../api/MarketApi.js'	
	export default {
		data() {
			return {
				goodsData:[],
				searchMap:{
					goodsName:'',
					goodsCategory:'',
					goodsLocation:'',
					lowPrice:'',
					highPrice:'',
				},
				imgPrefix:'http://localhost:9527/app/goodsImg/'
			}
		},
		methods: {
			openPopup(){
			// 通过组件定义的ref调用uni-popup方法 ,如果传入参数 ，type 属性将失效 ，仅支持 ['top','left','bottom','right','center']
			this.$refs.popup.open()
			},
			allGoods(){
				MarketApi.allGoods(this.searchMap).then(res=>res.data).then(res=>{
					this.goodsData=res.data
					for (const key in this.goodsData){
						const imgList=this.goodsData[key].goodsImg.split('/')
						this.goodsData[key].goodsImg=imgList[0]
						const goodsDetail=this.goodsData[key].goodsDetail.split('、')
						this.goodsData[key].goodsDetail=goodsDetail
					}
				})
			},
			toGoodsDetail(goodsID){
				uni.navigateTo({
					url: '/pages/goodsDetail/goodsDetail?goodsID='+goodsID
				})
			}
		},
		mounted(){
			this.allGoods()
		}
	}
</script>

<style lang="scss">
button::after{ border: none;} 
text{
	color: #683632;
}
button{
	width: 80px;
	height: 33px;
	margin: 5px 0px 0px 10px;
	border-radius: 20px;
	border:0px;
	font-size: 13px;
	color: #EAEEF1;
	background-color: #CB523E;
}
.ipt::placeholder{
    color: #EAEEF1;
}
input{
	color: #EAEEF1;
	background-color: #CB523E;
	width: 120px;
	height: 30px;
	margin-right: 10px;
	margin-left: 20px;
	margin-top: 5px;
	text-align: center;
	border-radius: 15px;
}
.filterPopup{
	width: 100%;
	height: 600rpx;
	padding-top: 10px;
	background-color: #DFD7C2;
	border-bottom-left-radius: 15px;
	border-bottom-right-radius: 15px;
	display: flex;
	flex-direction: column;
	text{
		margin-left: 10px;
		margin-top: 10px;
		font-size: 16px;
		font-weight: bold;
	}
	.filterType{
		display: flex;
		flex-direction: row;
		margin-left: 10px;
	}
}
.goodsSearch{
	display: flex;
	flex-direction: column;
}
.typeTitle{
	font-size: 25px;
	font-weight: bold;
	text-align: center;
	margin-top: 10px;
}
.goodsTotal{
	display: flex;
	flex-direction: column;
	width: 100%;
	margin-top: 10px;
	background-color: antiquewhite;
	border-top-left-radius: 15px;
	border-top-right-radius: 15px;
	.filter{
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 10px 50px 10px 50px;
	}
	.goodsList{
		width: 100%;
		margin-left: 10px;
		display: flex;
		flex-direction: row;
		margin-bottom: 15px;
		image{
			width: 130px;
			height: 130px;
			border-radius: 15px;
		}
		.goodsInfor{
			display: flex;
			flex-direction: column;
			.uni-tag{
				margin-right: 5px;
			}
			.inforList{
				margin-left: 10px;
				.price{
					font-weight: bold;
					font-size: 20px;
					color: #b93a26;
					margin-right: 3px;
				}
			}
		}
	}
}
</style>
