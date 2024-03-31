import axios from "axios";
axios.defaults.baseURL='http://localhost:9527/app'
class MarketApi{
	
	//查看商品详情功能
	goodsDetail(goodsID){
		return axios.get('/mark/goodsDetail?goodsID='+goodsID)
	}
	//查询符合条件的所有商品
	allGoods(searchMap){
		return axios.post('/mark/allGoods',searchMap)
	}
	//查询购物车
	selectCart(userID,){
		return axios.get('/mark/selectCart?userID='+userID)
	}
	//删除购物车
    deleteCart(userID,goodsID){
		return axios.get('/mark/deleteCart?userID='+userID+"&goodsID="+goodsID)
	}
	//购物车商品变动
	addCart(userID,goodsID,goodsNumber){
		return axios.get('/mark/addCart?userID='+userID+"&goodsID="+goodsID+"&goodsNumber="+goodsNumber)
	}
	
	//选定商品并计算总价
	checkSum(userID,goodsID,isCheck){
		return axios.get('/mark/checkSum?userID='+userID+"&goodsID="+goodsID+"&isCheck="+isCheck)
	}
	
	//查看用户所有的订单
	userOrders(userID){
		return axios.get('/mark/userOrders?userID='+userID)
	}
	
	//展示购买的商品
	buyGoods(userID){
		return axios.get('/mark/buyGoods?userID='+userID)
	}
	
	//查看订单详情
	selectOrders(ordersID){
		return axios.get('/mark/selectOrders?ordersID='+ordersID)
	}
	
	//生成订单
	addOrders(orders){
		return axios.post('/mark/addOrders',orders)
	}
	
	//删除订单
	delOrder(userID,ordersID){
		return axios.get('/mark/delOrders?userID='+userID+"&ordersID="+ordersID)
	}
	
	//查看具体地址
	getAddress(addressID){
		return axios.get('/mark/getAddress?addressID='+addressID)
	}
	
	//查看默认地址
	defaultAddress(userID){
		return axios.get('/mark/defaultAddress?userID='+userID)
	}
	
	//查看收货地址
	showAddress(userID){
		return axios.get('/mark/showAddress?userID='+userID)
	}
	
	//添加收货地址
	addAddress(userAddress){
		return axios.post('/mark/addAddress',userAddress)
	}
	
	//删除收货地址
	delAddress(userAddress){
		return axios.post('/mark/delAddress',userAddress)
	}
	//修改收货地址
	updateAddress(userAddress){
		return axios.post('/mark/updateAddress',userAddress)
	}
}
export default new MarketApi()
