
import{ render,screen } from '@testing-library/react';
import Login from '../Login';

test('checking component rendering', () => {
  const { getByText } = render(<Login />);
//   const linkElement = getByText(/Login/i);
//   expect(linkElement).toBeInTheDocument();
//steps for testing the Login component loaded

// render(<Login />);

// expect(screen.quertByText(/Login/)).toBeInTheDocument();
// expect(screen.queryByText(/Login/)).toBeInTheDocument();

});