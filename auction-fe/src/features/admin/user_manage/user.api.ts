import {appApi} from "../../../api";
import {BaseResponse, User} from "../../../model/user";

export const userApi = appApi.injectEndpoints({
    endpoints: (builder) => ({
        getAllUsers: builder.query<BaseResponse<User[]>, void>({
            query: () => ({
                url: 'user/getAllUsers'
            }),
        }),
    }),
})
export const {
    useGetAllUsersQuery,
} = userApi;