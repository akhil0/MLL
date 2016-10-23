module.exports = function(config) {
    config.set({
        basePath: '',

        frameworks: ['jasmine'],

        files: [
            './dist/styles/bootstrap-*.css',
            './dist/styles/styles-*.css',

            './dist/scripts/jquery-*.js',
            './dist/scripts/bootstrap-*.js',
            './dist/scripts/angular-*.js',
            './dist/scripts/app-templates-*.js',
            './dist/scripts/app-*.js',

            './tests/**/*.js'
        ],

        exclude: [
        ],

        preprocessors: {
        },

        plugins : [
            'karma-jasmine',
            'karma-chrome-launcher',
            'karma-junit-reporter'
        ],

        reporters: ['dots', 'junit'],

        colors: true,

        logLevel: config.LOG_INFO,

        autoWatch: false,

        browsers: ['Chrome'],

        singleRun: true,

        concurrency: Infinity,

        junitReporter: {
            outputDir: 'dist/',
            outputFile: 'test-results.xml',
            useBrowserName: false
        }
    })
};