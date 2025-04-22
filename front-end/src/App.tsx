import { useForm } from 'react-hook-form';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import axios from 'axios';
import './App.css';
import Home from './pages/Home/Home';

// Schema with validation rules
const suburbSchema = z.object({
  name: z.string().min(1, 'Suburb name is required'),
  postcode: z
    .string()
    .min(4, 'Postcode must be at least 4 digits')
    .max(4, 'Postcode must be exactly 4 digits')
    .regex(/^\d+$/, 'Postcode must be numeric'),
});

// Type derived from the schema
type CreateSuburbDTO = z.infer<typeof suburbSchema>;

function App() {
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset
  } = useForm<CreateSuburbDTO>({
    resolver: zodResolver(suburbSchema),
  });

  const onSubmit = async (data: CreateSuburbDTO) => {
    try {
      const response = await axios.post('http://localhost:8080/suburbs', data);
      console.log('Suburb created:', response.data);
      reset(); // reset the form after successful submit
    } catch (error) {
      console.error('Error creating suburb:', error);
    }
  };

  return (
    <>
      <Home />
    <div className="form-wrapper">
      <h2>Create Suburb</h2>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div>
          <label>Suburb Name:</label>
          <input type="text" {...register('name')} />
          {errors.name && <p className="error">{errors.name.message}</p>}
        </div>

        <div>
          <label>Postcode:</label>
          <input type="text" {...register('postcode')} />
          {errors.postcode && <p className="error">{errors.postcode.message}</p>}
        </div>

        <button type="submit">Submit</button>
      </form>
    </div>
 
    </>
  );
}

export default App;
