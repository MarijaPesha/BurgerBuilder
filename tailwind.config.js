const defaultTheme = require('tailwindcss/defaultTheme')
module.exports = {
  future: {
    removeDeprecatedGapUtilities: true,
  },
  theme: {
    extend: {
      transitionDelay: {
       '0': '0ms',
       '2000': '2000ms',
      }
    }
  },
  variants: {
    extend: {
      opacity: ['disabled'],
    }
  }
};