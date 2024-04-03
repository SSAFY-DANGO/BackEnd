import React from 'react'
import { RouterProvider } from 'react-router-dom'
import routes from './routes/Route'




export default function App() {
  return (
    <>
    <RouterProvider router={routes}></RouterProvider>
    </>
    
  )
}
