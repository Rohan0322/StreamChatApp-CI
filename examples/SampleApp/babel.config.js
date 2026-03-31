module.exports = {
  presets: ['module:@react-native/babel-preset'],
  plugins: [
    'babel-plugin-react-compiler',
    // must be last
    'react-native-reanimated/plugin',
  ],
};
