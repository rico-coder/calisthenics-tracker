/**
 * Author: Rico Krenn
 * Created: 06/22/2026
 * Version: 1.0
 * Description: Exercises Page (AI helped with design)
 * Project: 200_Abschlussprojekt
 */

import { useState, useEffect } from 'react'
import { Container, Card, Form, Button } from 'react-bootstrap'
import api from '../api/axios'

function Exercises() {
    const [exercises, setExercises] = useState([])
    const [progressions, setProgressions] = useState([])
    const [name, setName] = useState('')
    const [muscleGroup, setMuscleGroup] = useState('')
    const [newLevelInputs, setNewLevelInputs] = useState({})

    const fetchExercises = () => {
        api.get('/api/exercises').then(res => setExercises(res.data))
    }
    const fetchProgressions = () => {
        api.get('/api/progression').then(res => setProgressions(res.data))
    }

    useEffect(() => {
        fetchExercises()
        fetchProgressions()
    }, [])

    // Looks up only the progression levels belonging to one exercise and sorts them after the Index
    const getLevelsFor = (exerciseId) =>
        progressions
            .filter(p => p.exercise.id === exerciseId)
            .sort((a, b) => a.orderIndex - b.orderIndex)

    const updateLevelInput = (exerciseId, field, value) => {
        setNewLevelInputs({
            ...newLevelInputs,
            [exerciseId]: { ...newLevelInputs[exerciseId], [field]: value }
        })
    }

    const handleAddExercise = async (e) => {
        e.preventDefault()
        try {
            await api.post('/api/exercises', { name, muscleGroup })
            setName('')
            setMuscleGroup('')
            fetchExercises()
        } catch (err) {
            console.log(err)
            alert('Something went wrong')
        }
    }

    const handleAddLevel = async (exerciseId) => {
        const input = newLevelInputs[exerciseId] || {}
        try {
            await api.post('/api/progression', {
                name: input.name,
                orderIndex: parseInt(input.orderIndex) || 1,
                isCurrent: false,
                exercise: { id: exerciseId }
            })
            fetchProgressions()
            setNewLevelInputs({
                ...newLevelInputs,
                [exerciseId]: { name: '', orderIndex: '' }
            })
        } catch (err) {
            console.log(err)
            alert('Something went wrong')
        }
    }

    const handleDelete = async (exerciseId) => {
        try {
            await api.delete('/api/exercises/' + exerciseId)
            fetchExercises()
            fetchProgressions()
        } catch (err) {
            console.log(err)
            alert('Could not delete. It may be used in workout history.')
        }
    }

    return (
        <Container>
            <h1 className="fw-bold mt-4">Exercises</h1>

            <Form onSubmit={handleAddExercise} className="d-flex gap-2 mb-4 mt-4">
                <Form.Control placeholder="Name" value={name} onChange={e => setName(e.target.value)} />
                <Form.Control placeholder="Muscle group" value={muscleGroup} onChange={e => setMuscleGroup(e.target.value)} />
                <Button type="submit">Add</Button>
            </Form>

            {exercises.map(exercise => (
                <Card key={exercise.id} className="mb-3">
                    <Card.Body>
                        <div className="d-flex justify-content-between align-items-center">
                            <div>
                                <strong>{exercise.name}</strong>
                                <div className="text-muted">{exercise.muscleGroup}</div>
                            </div>
                            <Button variant="outline-danger" size="sm" onClick={() => handleDelete(exercise.id)}>
                                Delete
                            </Button>
                        </div>

                        <div className="mt-3">
                            {getLevelsFor(exercise.id).map(level => (
                                <div key={level.id}>{level.name}</div>
                            ))}
                        </div>

                        <div className="d-flex gap-2 mt-2">
                            <Form.Control
                                size="sm"
                                placeholder="New level name"
                                value={newLevelInputs[exercise.id]?.name || ''}
                                onChange={e => updateLevelInput(exercise.id, 'name', e.target.value)}
                            />
                            <Form.Control
                                size="sm"
                                type="number"
                                placeholder="Order"
                                value={newLevelInputs[exercise.id]?.orderIndex || ''}
                                onChange={e => updateLevelInput(exercise.id, 'orderIndex', e.target.value)}
                            />
                            <Button size="sm" onClick={() => handleAddLevel(exercise.id)}>Add</Button>
                        </div>
                    </Card.Body>
                </Card>
            ))}
        </Container>
    )
}

export default Exercises