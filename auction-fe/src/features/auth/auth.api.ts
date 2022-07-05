import {BaseResponse, LoginResponse} from "../../model/user.model";
import {appApi} from "../../api";

export const authApi=appApi.injectEndpoints({
        endpoints: (builder) => ({
        login: builder.mutation<BaseResponse<LoginResponse>, {username:string,password:string}>({
            query: (credentials) => ({
                url: 'user/login',
                method: 'POST',
                body: credentials,
            }),
        }),
        register:builder.mutation<boolean,{username:string,password:string,name:string,address:string,email:string,dob:string,balance:number}>({
            query: (arg) => ({
                url: 'user/register',
                method: "POST",
                body: arg,
            }),
        })
    }),
})
export const {useLoginMutation,useRegisterMutation} = authApi;