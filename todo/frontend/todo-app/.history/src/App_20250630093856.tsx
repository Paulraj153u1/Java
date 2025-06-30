import { useEffect, useState } from 'react';
import { Todo } from './types';
import { fetchTodos, addTodo, updateTodo, deleteTodo } from './api';

function App() {
  const [todos, setTodos] = useState<Todo[]>([]);
  const [text, setText] = useState('');
  
  useEffect(() => { 
    fetchTodos().then(data => {
      console.log('Fetched todos:', data);
      
    });
  }, []);

  const handleAdd = async () => {
    if (text.trim()) {
      console.log('Adding todo:', text);
      
      const newTodo = await addTodo({ text, completed: false });
      setTodos([...todos, newTodo]);
      setText('');
    }
  };

  const handleToggle = async (t: Todo) => {
    const updated = await updateTodo({ ...t, completed: !t.completed });
    setTodos(todos.map(td => td.id === t.id ? updated : td));
  };

  const handleDel = async (id: number) => {
    await deleteTodo(id);
    setTodos(todos.filter(t => t.id !== id));
  };

  return (
    <div style={{ maxWidth: 400, margin: '0 auto', padding: '2rem' }}>
      <h1>To‑Do List</h1>
      <div>
        <input
          value={text}
          onChange={e => setText(e.target.value)}
          placeholder="New task"
        />
        <button onClick={handleAdd}>Add</button>
      </div>
      <ul>
        {todos.map(t => (
          <li key={t.id} style={{ display: 'flex', alignItems: 'center' }}>
            <input
              type="checkbox"
              checked={t.completed}
              onChange={() => handleToggle(t)}
            />
            <span style={{ margin: '0 8px', textDecoration: t.completed ? 'line-through' : undefined }}>
              {t.text}
            </span>
            <button onClick={() => handleDel(t.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
