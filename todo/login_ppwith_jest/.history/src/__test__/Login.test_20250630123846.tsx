
import{ render,screen } from '@testing-library/react';
import Login from '../Login';
import '@testing-library/jest-dom';


test('renders Login heading', () => {
  render(<Login />);
  const heading = screen.getByRole('heading', { name: /Login/i });
  expect(heading).toBeInTheDocument();
});

test("checking for email input", () => {
  render(<Login />);
  // const emailInput = screen.getByPlaceholderText(/Email/i).tohaveValue('');
  expect(screen.getByPlaceholderText(/Email/i)).toBeInTheDocument().toHaveValue('');
  // expect(emailInput).toBeInTheDocument().tohaveValue('');
});
test('renders password input', () => {
  render(<Login />);
  const passwordInput = screen.getByPlaceholderText(/Password/i);
  expect(passwordInput).toBeInTheDocument();
});




