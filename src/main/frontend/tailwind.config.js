/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["../resources/templates/**/*.{html,js}"],
  theme: {
    extend: {
      colors: {
        "cola-red": "#c00a27",
        "cola-dark": "#1a1a1a",
        "cola-gold": "#d4af37",
      },
      backgroundImage: {
        "cola-gradient":
          "radial-gradient(circle, rgba(224, 202, 202, 1) 0%, rgba(255, 0, 0, 1) 91%)",
      },
      fontFamily: {
        display: ['"Bebas Neue"', "sans-serif"],
        body: ['"Open Sans"', "sans-serif"],
      },
      spacing: {
        '30': '7.5rem',
        '40': '10rem',
      },
    },
  },
  injectGlobalStyles: {
    "@import": [
      "https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Open+Sans:wght@400;600&display=swap",
    ],
  },
  plugins: [],
};
