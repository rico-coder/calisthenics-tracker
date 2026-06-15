import './App.css'

/**
 * Author: Rico Krenn
 * Created: 06/15/2026
 * Version: 1.0
 * Description: Routes
 * Project: 200_Abschlussprojekt
 */

import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'

function ProtectedRoute({ children }) {
  const token = localStorage.getItem('token')
  if (!token) {
    return <Navigate to="/login" />
  }
  return children
}

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<div>Login page</div>} />
          <Route path="/" element={
            <ProtectedRoute>
              <div>Dashboard</div>
            </ProtectedRoute>
          } />
          <Route path="/workouts" element={
            <ProtectedRoute>
              <div>Workouts</div>
            </ProtectedRoute>
          } />
          <Route path="/progressions" element={
            <ProtectedRoute>
              <div>Progressions</div>
            </ProtectedRoute>
          } />
          <Route path="/exercises" element={
            <ProtectedRoute>
              <div>Exercises</div>
            </ProtectedRoute>
          } />
          <Route path="/stats" element={
            <ProtectedRoute>
              <div>Stats</div>
            </ProtectedRoute>
          } />
        </Routes>
      </BrowserRouter>
  )
}

export default App