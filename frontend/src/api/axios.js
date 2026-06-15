import axios from 'axios'

/**
 * Author: Rico Krenn
 * Created: 06/15/2026
 * Version: 1.0
 * Description: Axios acts like a messanger between the front- and backend.
 * Project: 200_Abschlussprojekt
 */

const api = axios.create({
    baseURL: 'http://localhost:8080'
})

api.interceptors.request.use(config => {
    const token = localStorage.getItem('token')
    if (token) {
        config.headers.Authorization = `Bearer ${token}`
    }
    return config
})

export default api