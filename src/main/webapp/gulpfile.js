'use strict';

let gulp = require('gulp');

let jshint = require('gulp-jshint');
let stylish = require('jshint-stylish');

let del = require('del');

let templateCache = require('gulp-angular-templatecache');
let htmlmin = require('gulp-htmlmin');

let usemin = require('gulp-usemin');
let uglify = require('gulp-uglify');
let minifycss = require('gulp-minify-css');
let rev = require('gulp-rev');

let server = require('karma').Server;

/*
    Code Quality Task
*/
gulp.task('jshint', () => {
   gulp.src(['./source/scripts/**/*.js', '!./source/scripts/modules/templates/*.js'])
       .pipe(jshint())
       .pipe(jshint.reporter(stylish));
});

/*
    Clean-Up Task
*/
gulp.task('cleanup', () => del(['./dist', './index.html']));

/*
    Usemin Task
*/
gulp.task('usemin', ['templates'], function () {
    return gulp.src('./source/index.html')
        .pipe(usemin({
            cssBootstrap: [minifycss(), rev()],
            cssCustom: [minifycss(), rev()],
            jsJQuery: [uglify(), rev()],
            jsBootstrap: [uglify(), rev()],
            jsAngular: [uglify(), rev()],
            jsTemplates: [uglify(), rev()],
            jsCustom: [rev()]
        }))
        .pipe(gulp.dest('./'));
});

/*
    Template-Cache Task
*/
gulp.task('templates', () => {
    return gulp.src('./source/scripts/**/*.html')
        .pipe(htmlmin({ collapseWhitespace: true }))
        .pipe(templateCache({
            module: 'mllApp.templates',
            standalone: true,
            filename: 'templates.module.js',
            transformUrl: (url) => url.slice(url.lastIndexOf('\\') + 1)
        }))
        .pipe(gulp.dest('./source/scripts/modules/templates/'));
});

/*
    Fonts Task
*/
gulp.task('fonts', () => {
    gulp.src('./bower_components/font-awesome/fonts/**/*.{ttf,woff,eof,svg}*')
        .pipe(gulp.dest('./dist/fonts'));
    gulp.src('./bower_components/bootstrap/dist/fonts/**/*.{ttf,woff,eof,svg}*')
        .pipe(gulp.dest('./dist/fonts'));
    gulp.src('./source/fonts/*.*')
        .pipe(gulp.dest('./dist/fonts'));
});

/*
    Tests Task
*/
gulp.task('tests', ['usemin', 'fonts'], function (done) {
    new server({
        configFile: __dirname + '/karma.conf.js',
        singleRun: true
    }, done).start();
});

/*
    Default Task
 */
gulp.task('default', ['cleanup', 'jshint'], function() {
    gulp.start('tests');
});