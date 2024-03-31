<template>
	<view class="addressPage">
		
	<scroll-view class="content" scroll-y>	
		<view class="addressInfor" v-for="userAddress in userAddress" :key="userAddress.name" @click="toPay(userAddress.addressID)">
			<view class="inforIcons">
				<text>吕</text>
			</view>
			<view class="inforRight">
				<view class="inforMain">
					<text>{{userAddress.name}}</text>
					<uni-tag v-if="userAddress.weight==1" text="默认" size="mini" type="warning" :inverted="true"></uni-tag>
				</view>
				<view class="inforMain">
					<text>{{userAddress.phone}}</text>
					<uni-icons type="compose" size="24" @click="openPopup(userAddress)"></uni-icons>
				</view>
				<view class="inforMain">
					<text>{{userAddress.province}}</text>
					<text>{{userAddress.city}}</text>
					<text>{{userAddress.detailAddress}}</text>
				</view>
			</view>
		</view>
		
	</scroll-view>
		
		<view class="addAddress">
			<button @click="openAddPopup()">+添加地址</button>
		</view>
		
		<uni-popup ref="popup" type="top">
			<view class="editAddress">
				<view class="addressList">
					<text>收件人</text>
					<input v-model="addressForm.name" />
				</view>
				<view class="addressList">
					<text>手机号</text>
					<input v-model="addressForm.phone" />
				</view>
				<view class="addressList">
					<text>所在省</text>
					<input v-model="addressForm.province" />
				</view>
				<view class="addressList">
					<text>所在市</text>
					<input v-model="addressForm.city" />
				</view>
				<view class="addressList">
					<text>详细地址</text>
					<textarea v-model="addressForm.detailAddress" />
				</view>
				<view class="addressList">
					<text>设为默认收货地址</text>
					<checkbox
					value="dd"
					style="margin-right: 0px;margin-left: 110px;"
					:checked="addressForm.weight"
					@click="changeWeight()"
					></checkbox>
				</view>
				<button @click="updateAddress(addressForm)">保存</button>
			</view>
		</uni-popup>
		
		<uni-popup ref="addPopup" type="top">
			<view class="editAddress">
				<view class="addressList">
					<text>收件人</text>
					<input v-model="addressForm.name" />
				</view>
				<view class="addressList">
					<text>手机号</text>
					<input v-model="addressForm.phone" />
				</view>
				<view class="addressList">
					<text>所在省</text>
					<input v-model="addressForm.province" />
				</view>
				<view class="addressList">
					<text>所在市</text>
					<input v-model="addressForm.city" />
				</view>
				<view class="addressList">
					<text>详细地址</text>
					<textarea v-model="addressForm.detailAddress" />
				</view>
				<view class="addressList">
					<text>设为默认收货地址</text>
					<checkbox
					value="dd"
					style="margin-right: 0px;margin-left: 110px;"
					:checked="addressForm.weight"
					@click="changeWeight()"
					></checkbox>
				</view>
				<button @click="addAddress(addressForm)">添加地址</button>
			</view>
		</uni-popup>
		
	</view>
</template>

<script>
	import MarketApi from '../../api/MarketApi.js'
	export default {
		data() {
			return {
				CurUser:'',
				userAddress:[],
				addressForm:{
					userID:'',
					name:'',
					phone:'',
					province:'',
					city:'',
					deltailAddress:'',
					weight:''
				}
			}
		},
		methods: {
			openPopup(userAddress){
				this.addressForm.addressID=userAddress.addressID
				this.addressForm.userID=this.CurUser.userID
				this.addressForm.name=userAddress.name
				this.addressForm.phone=userAddress.phone
				this.addressForm.province=userAddress.province
				this.addressForm.city=userAddress.city
				this.addressForm.detailAddress=userAddress.detailAddress
				if(userAddress.weight==0){
					this.addressForm.weight=false
				}else{
					this.addressForm.weight=true
				}
				this.$refs.popup.open()
			},
			openAddPopup(){
				this.addressForm.addressID=''
				this.addressForm.userID=this.CurUser.userID
				this.addressForm.name=''
				this.addressForm.phone=''
				this.addressForm.province=''
				this.addressForm.city=''
				this.addressForm.detailAddress=''
				this.addressForm.weight=false
				this.$refs.addPopup.open()
			},
			getAddress(){
				MarketApi.showAddress(this.CurUser.userID).then(res=>res.data).then(res=>{
					this.userAddress=res.data
				})
			},
			addAddress(userAddress){
				if(userAddress.weight==true){
					userAddress.weight=1
				}else{
					userAddress.weight=0
				}
				MarketApi.addAddress(userAddress).then(res=>res.data).then(res=>{
					uni.showToast({
						title: `添加地址成功`,
						icon: 'none'
					})
					this.getAddress()
					this.$refs.addPopup.close()
				})
			},
			changeWeight(){
				this.addressForm.weight=!this.addressForm.weight
			},
			updateAddress(userAddress){
				if(userAddress.weight==true){
					userAddress.weight=1
				}else{
					userAddress.weight=0
				}
				MarketApi.updateAddress(userAddress).then(res=>res.data).then(res=>{
					uni.showToast({
						title: `修改地址成功`,
						icon: 'none'
					})
					this.$refs.popup.close()
					this.getAddress()
				})
			},
			toPay(addressID){
				uni.navigateTo({
					url:'/pages/payPage/payPage?addressID='+addressID
				})
			}
		},
		mounted(){
			this.CurUser=JSON.parse(localStorage.getItem("CurUser"))
			this.getAddress()
		}
	}
</script>

<style lang="scss">
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
.content {
	flex: 1;
	overflow: auto;
}	
.addressPage{
	display: flex;
	flex-direction: column;
	height: 94vh;
	background-color: antiquewhite;
}
.addAddress{
	width: 100%;
	height: 120rpx;
	display: flex;
	flex-direction: row;
	background-color: #95302e;
	button{
		width: 280px;
		margin-top: 8px;
		height: 40px;
		border-radius: 15px;
	}
}
.editAddress{
	width: 100%;
	border-bottom-left-radius: 15px;
	border-bottom-right-radius: 15px;
	height: 380px;
	background-color: antiquewhite;
	display: flex;
	flex-direction: column;
	padding: 20px;
	button{
		width: 300px;
		margin-left: 20px;
		border-radius: 15px;
		border:0px;
		color: #EAEEF1;
		background-color: #CB523E;
	}
	.addressList{
		display: flex;
		flex-direction: row;
		margin-bottom: 20px;
		text{
			font-size: 18px;
			font-weight: bold;
			margin-right: 15px;
		}
		input{
			background-color: #cb523e;
			color: #ffffff;
			border-radius: 5px;
			margin-left: 15px;
			text-indent:10px;
			height:30px;
			width: 200px;
		}
		textarea{
			background-color: #cb523e;
			color: #ffffff;
			width: 200px;
			height: 70px;
			border-radius: 10px;
			text-indent:10px
		}
		radio{
			margin-left: 100px;
		}
	}
}
.addressInfor{
	display: flex;
	flex-direction: row;
	margin-left: 10px;
	margin-bottom: 10px;
	padding-left: 10px;
	background-color: #f5f2e9;
	border: 1px solid #d4d3ca;
	border-radius: 15px;
	width: 680rpx;
	height: 210rpx;
	.inforIcons{
		width: 50px;
		height: 50px;
		background-color: #cb523e;
		border-radius: 50%;
		text-align: center;
		margin-top: 20px;
		text{
			font-weight: bold;
			font-size: 20px;
			line-height: 50px;
		}
	}
	.inforRight{
		display: flex;
		flex-direction: column;
		margin-left: 10px;
		margin-top: 10px;
		.uni-icons{
			margin-left: 120px;
		}
		text{
			font-size: 15px;
			margin-right: 5px;
		}
	}
}
</style>
