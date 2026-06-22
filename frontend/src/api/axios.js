/**
 * Author: Rico Krenn
 * Created: 06/15/2026
 * Version: 2.0
 * Description: Axios acts like a messanger between the front- and backend.
 * Project: 200_Abschlussprojekt
 */

import axios from 'axios'

const api = axios.create({
    baseURL: import.meta.env.VITE_API_URL || ''
})

api.interceptors.request.use(config => {
    const token = localStorage.getItem('token')
    if (token) {
        config.headers.Authorization = `Bearer ${token}`
    }
    return config
})

export default api