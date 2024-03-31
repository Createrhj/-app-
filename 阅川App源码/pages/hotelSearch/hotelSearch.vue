<template>
	<view class="goodsSearch">
		<uni-search-bar radius="100" placeholder="请输入搜索内容" clearButton="none" />
		<view class="goodsTotal">
			<view class="filter"style="font-size: 16px;margin-top: -15px;margin-bottom: 10px;">
				<text style="color: #631216;font-weight: bold;">综合</text>
				<text style="color: #631216;font-weight: bold;">价格</text>
				<text style="color: #631216;font-weight: bold;">销量</text>
				<text @click="openPopup" style="color: #631216;font-weight: bold;">筛选</text>
			</view>
			
			<view class="goodsList" @click="toHotelDetail(hotel.hotelID)" v-for="hotel in hotelData" :key="hotel.hotelID">
				<image :src="imgPrefix+hotel.hotelImg" mode="aspectFill"></image>
				<view class="goodsInfor">
					<view class="inforList" style="margin-top: 2px;font-weight: bold;">
						<text>{{hotel.hotelName}}</text>
					</view>
					<view class="inforList">
						<uni-tag :text="hotel.hotelCount"
							style="background-color: #cb523e; border-color: #cb523e; color: #fff;"/>
						<text>很好</text>
					</view>
					<view class="inforList">
						<text>成都天府广场</text>
					</view>
					<view class="inforList" style="margin-bottom: 0;">
						<uni-tag 
						style="background-color: #b93a26;border-color:#b93a26; color: #fff;"
						:inverted="true" :text="hotel.hotelEvaluate[0]" size="small"/>
						<uni-tag
						 style="background-color: #b93a26;border-color:#b93a26; color: #fff;"
						 :inverted="true" :text="hotel.hotelEvaluate[1]" size="small"/>

					</view>
					<text class="price">￥{{hotel.hotelPrice}}元</text>
				</view>
			</view>
	
		</view>
		
		<uni-popup ref="popup" type="top">
			<view class="filterPopup">
				<text>酒店类型</text>
				<view class="filterType">
					<button>酒店</button>
					<button>民宿</button>
					<button>公寓</button>
				</view>
				<view class="filterType">
					<button>酒店公寓</button>
					<button>钟点房</button>
					<button>青年旅馆</button>
				</view>
				<text style="margin-top: 20px;">价格区间</text>
				<view class="filterType">
					<input placeholder="自定义最低价"/>
					<uni-icons type="arrow-right" size="20"></uni-icons>
					<input placeholder="自定义最高价"/>
				</view>
				<text style="margin-top: 20px;">床型</text>
				<view class="filterType">
					<button>大床房</button>
					<button>三床房</button>
					<button>双床房</button>
				</view>
				<view class="filterType">
					<button>单人床房</button>
					<button>多床房</button>
					<button>特大床房</button>
				</view>
				<view class="filterType">
					<button class="btn">取消</button>
					<button class="btn">确定筛选</button>
				</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
	import TravelApi from '../../api/TravelApi.js'
	export default {
		data() {
			return {
				hotelData:[],
				searchMap:{
					hotelName:'',
					hotelCategory:'',
					hotelCity:'',
					lowPrice:'',
					highPrice:'',
				},
				imgPrefix:'http://localhost:9527/app/hotelImg/'
			}
		},
		methods: {
			openPopup(){
			this.$refs.popup.open()
			},
			
			allHotel(){
				TravelApi.allHotel(this.searchMap).then(res=>res.data).then(res=>{
					this.hotelData=res.data
					for (const key in this.hotelData){
						const imgList=this.hotelData[key].hotelImg.split('/')
						const evaluate=this.hotelData[key].hotelEvaluate.split('、')
						this.hotelData[key].hotelEvaluate=evaluate
						this.hotelData[key].hotelImg=imgList[0]
					}
					console.log(this.hotelData)
				})
			},
			toHotelDetail(hotelID){
				uni.navigateTo({
					url: '/pages/hotelDetail/hotelDetail?hotelID='+hotelID
				})
			}
		},
		mounted(){
			this.allHotel()
		}
	}
</script>

<style lang="scss">
text{
	color: #683632;
}
button::after{ border: none;} 
.btn{
	margin-left: 15px;
	margin-right: 10Apx;
	margin-top: 20px;
	width: 150px;
	height: 40px;
	border-radius: 10px;
	font-size: 17px
}
button{
	width: 80px;
	height: 30px;
	margin: 5px 0px 0px 10px;
	border-radius: 20px;
	border:0px;
	font-size: 13px;
	color: #EAEEF1;
	background-color: #CB523E;
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
	height: 750rpx;
	padding-top: 10px;
	background-color: #FFFFFF;
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
				margin-bottom: 5px;
			}
			.price{
				font-weight: bold;
				font-size: 20px;
				color: #b93a26;
				margin-left: 135px;
			}
		}
	}
}
</style>
