import axios from 'axios'
import global from '../components/global'
const baseURL = 'http://localhost:8080'
axios.defaults.baseURL = baseURL
axios.defaults.timeout = 10000

export const check = async (username, password) => {
    try {
        const response = await axios({
            method: 'get',
            url: `/check`,
            auth: {
                username: username,
                password: password,
            },
        })
        return response
    } catch (error) {
        console.error(error)
    }
}

export const chat_model = async (model, text) => {
    try {
        const response = await axios.get({
            url: `/ai/chat/${model}/?msg=${text}`,
            auth: {
                username: global.username,
                password: global.password
            },
        })
        return response
    } catch (error) {
        console.error(error)
    }
}
import {Base64} from 'js-base64'
export const stream_chat_model = async (model, text) => {
    try {
        let username = global.username;
        let headers = new Headers();
        headers.set('Authorization', 'Basic ' + Base64.encode(username + ":" + global.password));

        const response = await fetch(baseURL+`/ai/stream_chat/${model}?username=${username}&msg=${text}`,{
            headers: headers,
        });
        // const response = await fetch(baseURL+`/ai/stream_chat/${model}/?msg=${text}`);
        const reader = response.body.getReader();
        return reader;
    } catch (error) {
        console.error(error)
    }
}
export const mapLog = async () => {
    try {
        let username = global.username;
        const response = await axios({
            method: 'get',
            url: `/log/map/${username}`,
            auth: {
                username: username,
                password: global.password,
            },
        })
        return response
    } catch (error) {
        console.error(error)
    }
}
export const mapAllLog = async () => {
    try {
        let username = global.username;
        const response = await axios({
            method: 'get',
            url: `/log/map`,
            auth: {
                username: username,
                password: global.password,
            },
        })
        return response
    } catch (error) {
        console.error(error)
    }
}

export const delLog = async (id) => {
    try {
        const response = await axios({
            method: 'delete',
            url: `/log/${id}`,
            auth: {
                username: global.username,
                password: global.password,
            },
        })
        return response
    } catch (error) {
        console.error(error)
    }
}