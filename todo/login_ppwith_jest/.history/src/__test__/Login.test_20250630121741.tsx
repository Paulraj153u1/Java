
import{ render,screen } from '@testing-library/react';
import Login from '../Login';
import '@testing-library/jest-dom';


test('renders Login text', () => {
  render(<Login />);
expect(screen.getByText(/Login/i)).toBeInTheDocument();

});