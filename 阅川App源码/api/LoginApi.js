import axios from "axios";
axios.defaults.baseURL='http://localhost:9527/app'
class LoginApi{
	//用户登录
    userLogin(userAccount,userPassword){
        return axios.get('/login/userLogin?userAccount='+userAccount+"&userPassword="+userPassword)
    }
	//用户注册
    userRegister(user){
		return axios.post('/login/userRegister',user)
	}
}
export default new LoginApi()
