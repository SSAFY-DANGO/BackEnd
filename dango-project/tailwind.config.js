/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      fontFamily: {
        title: ['BMDOHYEON'],
        aggroS: ['SBAggroL'],
        aggroM: ['SBAggroM'],
        aggroB: ['SBAggroB'],
      },
    },
  },
  plugins: [],
};
