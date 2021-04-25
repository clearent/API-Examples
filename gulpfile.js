let gulp = require('gulp');

let SRC_DIR = 'src/';
let PHP_DIR = 'c:/wamp/www/php_example/';

let PHP_files = [SRC_DIR + '**/*.html', SRC_DIR + '**/*.php', SRC_DIR + '**/*.png', SRC_DIR + '**/*.css', SRC_DIR + '**/*.js'];


gulp.task('php', function () {
    "use strict";
    return gulp
        .src([SRC_DIR + '**/*'])
        .pipe(gulp.dest(PHP_DIR));
});

// Watch for file changes
gulp.task('watch', function () {
    "use strict";
    gulp.watch(PHP_files, gulp.series(['php']));
});

// Run all build steps and watch in development environment
gulp.task('default', gulp.series(['php', 'watch']));
