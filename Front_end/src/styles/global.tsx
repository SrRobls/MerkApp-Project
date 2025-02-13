import { StyleSheet } from 'react-native';
import { theme } from './theme';

export const globalStyles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: theme.colors.background,
    padding: theme.spacing.medium,
  },
  text: {
    color: theme.colors.text,
    fontSize: theme.fontSize.medium,
  },
  title: {
    color: theme.colors.text,
    fontSize: theme.fontSize.large,
    fontWeight: 'bold',
  },
  button: {
    backgroundColor: theme.colors.primary,
    padding: theme.spacing.medium,
    borderRadius: theme.borderRadius.medium,
    alignItems: 'center',
  },
  buttonText: {
    color: 'white',
    fontSize: theme.fontSize.medium,
    fontWeight: 'bold',
  },
});
