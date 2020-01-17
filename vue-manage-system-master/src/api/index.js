import request from '../utils/request';

export const fetchData = query => {
    return request({
        url: '/table',
        method: 'get',
        params: query
    });
};
