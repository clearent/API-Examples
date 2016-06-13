var concat = require('gulp-concat');
var gulp = require('gulp');
var minify = require('gulp-minify-css');
var uglify = require('gulp-uglify');
var del = require('del');
//var shell = require('gulp-shell');

var SRC_DIR      = 'src/';
var PHP_DIR       = 'C:/wamp/www/php_example/';

var PHP_files = [SRC_DIR + '**/*.html', SRC_DIR + '**/*.php', SRC_DIR + '**/*.png', SRC_DIR + '**/*.css', SRC_DIR + '**/*.js'];


gulp.task('php', function () {
    return gulp
        .src([SRC_DIR + '**/*'])
        .pipe(gulp.dest(PHP_DIR));
});

// Watch for file changes
gulp.task('watch', function () {
    gulp.watch(PHP_files, ['php']);
});

// Run all build steps and watch in development environment
gulp.task('default', ['php','watch']);
