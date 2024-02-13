module.exports = function(api) {
  api.cache(true);
  return {
    presets: ['babel-preset-expo'],
    plugins:[
      [
        'module-resolver',
        {
          alias: {
            // '@' : './src',
            '#' : './src',
            '@a' : './src/assets',
            '@c' : './src/components',
            '@co' : './src/context',
            '@cf' : './src/config',
            '@l' : './src/layouts',
            '@n' : './src/navigation',
            '@s' : './src/screens',
            '@se' : './src/services',
            '@st' : './src/styles',
            '@u' : './src/utils',
          }
        }
      ]
    ]
  };
};
