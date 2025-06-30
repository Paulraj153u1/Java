
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
  expect(screen.queryByPlaceholderText("Email")).toHaveValue('');
  expect(screen.queryByPlaceholderText("Password")).toHaveValue('');

});
test('renders password input', () => {
  render(<Login />);
  const passwordInput = screen.getByPlaceholderText(/Password/i);
  expect(passwordInput).toBeInTheDocument();
});




