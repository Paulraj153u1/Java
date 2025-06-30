import axios from 'axios';
import { Todo } from './types';

const api = axios.create({ baseURL: 'http://localhost:8080/api' });
export const fetchTodos = () => api.get<Todo[]>('/todos').then(r => r.data);
export const addTodo = (t: Partial<Todo>) => api.post('/todos', t).then(r => r.data);
export const updateTodo = (t: Todo) => api.put(`/todos/${t.id}`, t).then(r => r.data);
export const deleteTodo = (id: number) => api.delete(`/${id}`);