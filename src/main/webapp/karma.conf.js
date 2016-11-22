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
            'karma-coverage',
            'karma-junit-reporter',
            'karma-jenkins-reporter',
            'karma-phantomjs-launcher'
        ],

        reporters: ['dots', 'junit', 'progress', 'coverage', 'jenkins'],

        colors: true,

        logLevel: config.LOG_INFO,

        autoWatch: false,
        // only runs on headless browser
        browsers: ['PhantomJS'],

        singleRun: true,

        concurrency: Infinity,
        
        coverageReporter : {
            type : 'cobertura',
            dir  : 'target/coverage-reports/'
        },
        // saves report at `target/surefire-reports/TEST-*.xml` because Jenkins
        // looks for this location and file prefix by default.
        junitReporter: {
            outputDir: 'target/surefire-reports/',
            outputFile: 'test-results.xml',
            useBrowserName: false
        }
    })
};