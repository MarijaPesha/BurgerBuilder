const defaultTheme = require('tailwindcss/defaultTheme')
module.exports = {
  future: {
    removeDeprecatedGapUtilities: true,
  },
  theme: {
    extend: {
      keyframes: {
       slide: {
         '0%': { opacity: "0", transform: 'translateX(-50%)'},
         '25%': { opacity: "0.5", transform: 'translateX(-25%)'},
         '50%': { opacity: "0.8", transform: 'translateX(-10%)'},
         '100%': { opacity: "1", transform: 'translateX(0)'},
       }
      }
    }
  },
  theme: {
    extend: {
      animation: {
       'slide': 'slide 0.5s ease-in-out',
      }
    }
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