/**
 * Author: Rico Krenn
 * Created: 06/16/2026
 * Version: 1.0
 * Description: Progressions site
 * Project: 200_Abschlussprojekt
 */

import { useState, useEffect } from 'react'
import { Container, Card, Button, Badge } from 'react-bootstrap'
import api from '../api/axios'

function Progressions() {
    const [progressions, setProgressions] = useState([])

    const fetchProgressions = () => {
        api.get('/api/progression')
            .then(response => setProgressions(response.data))
            .catch(err => console.error(err))
    }

    useEffect(() => {
        fetchProgressions()
    }, [])

    // Group progressions by exercise name
    const grouped = progressions.reduce((acc, p) => {
        const name = p.exercise.name
        if (!acc[name]) acc[name] = []
        acc[name].push(p)
        return acc
    }, {})

    const handleSetCurrent = async (id) => {
        try {
            await api.put('/api/progression/' + id, { current: true })
            fetchProgressions()
        } catch (err) {
            console.error(err)
        }
    }

    return (
        <Container>
            <h1 className="fw-bold mt-4 text-body">Progressions</h1>
            {Object.entries(grouped).map(([exerciseName, levels]) => (
                <Card key={exerciseName} className="mb-3 mt-3">
                    <Card.Header><strong>{exerciseName}</strong></Card.Header>
                    <Card.Body>
                        {levels
                            .sort((a, b) => a.orderIndex - b.orderIndex)
                            .map(level => (
                                <div key={level.id} className="d-flex justify-content-between align-items-center mb-2">
                                    <span>
                                        {level.name}
                                        {level.current && <Badge bg="success" className="ms-2">Current</Badge>}
                                    </span>
                                    {!level.current && (
                                        <Button variant="outline-primary" size="sm" onClick={() => handleSetCurrent(level.id)}>
                                            Set as current
                                        </Button>
                                    )}
                                </div>
                            ))
                        }
                    </Card.Body>
                </Card>
            ))}
        </Container>
    )
}

export default Progressions