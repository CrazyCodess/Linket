module.exports = function (grunt) {

    // 构建任务配置
    grunt.initConfig({
        //读取package.json的内容，形成个json数据
        pkg: grunt.file.readJSON('package.json'),
        clean: ["web/dist", "www/dist"],
        //压缩js
        uglify: {
            //文件头部输出信息
            options: {
                banner: '/*! <%= pkg.name %> */\n'
            },
	    	web_commmonapp_uglify: {
                files: {'web/dist/common/js/app.js': ['web/common/js/app.js']}
            },
            web_commmon_uglify: {
                files: [
                    {
                        expand: true,
                        //相对路径
                        cwd: 'web/common/js',
                        src: '*/*.js',
                        dest: 'web/dist/common/js/',
                        rename: function (dest, src) {  
                                  var folder = src.substring(0, src.lastIndexOf('/'));  
                                  var filename = src.substring(src.lastIndexOf('/'), src.length);  
                                  //  var filename=src;  
                                  filename = filename.substring(0, filename.lastIndexOf('.'));  
                                  var fileresult=dest + folder + filename + '.js';  
                                  grunt.log.writeln("现处理文件："+src+"  处理后文件："+fileresult);  
            
                                  return fileresult;  
                                  //return  filename + '.min.js';  
                              } 
                    }
                ]
            },
	    	web_modules_uglify: {
                files: [
                    {
                        expand: true,
                        //相对路径
                        cwd: 'web/modules',
                        src: '*/*.js',
                        dest: 'web/dist/modules/',
                        rename: function (dest, src) {  
                                  var folder = src.substring(0, src.lastIndexOf('/'));  
                                  var filename = src.substring(src.lastIndexOf('/'), src.length);  
                                  //  var filename=src;  
                                  filename = filename.substring(0, filename.lastIndexOf('.'));  
                                  var fileresult=dest + folder + filename + '.js';  
                                  grunt.log.writeln("现处理文件："+src+"  处理后文件："+fileresult);  
                                  return fileresult;  
                              } 
                    }
                ]
            },
            app_commmon_uglify: {
                files: [
                    {
                        expand: true,
                        //相对路径
                        cwd: 'www/common/js',
                        src: '*/*.js',
                        dest: 'www/dist/common/js/',
                        rename: function (dest, src) {  
                                  var folder = src.substring(0, src.lastIndexOf('/'));  
                                  var filename = src.substring(src.lastIndexOf('/'), src.length);  
                                  //  var filename=src;  
                                  filename = filename.substring(0, filename.lastIndexOf('.'));  
                                  var fileresult=dest + folder + filename + '.js';  
                                  grunt.log.writeln("现处理文件："+src+"  处理后文件："+fileresult);  
            
                                  return fileresult;  
                              } 
                    }
                ]
            },
            app_modules_uglify: {
                files: [
                    {
                        expand: true,
                        //相对路径
                        cwd: 'www/modules',
                        src: '*/*.js',
                        dest: 'www/dist/modules/',
                        rename: function (dest, src) {  
                                  var folder = src.substring(0, src.lastIndexOf('/'));  
                                  var filename = src.substring(src.lastIndexOf('/'), src.length);  
                                  //  var filename=src;  
                                  filename = filename.substring(0, filename.lastIndexOf('.'));  
                                  var fileresult=dest + folder + filename + '.js';  
                                  grunt.log.writeln("现处理文件："+src+"  处理后文件："+fileresult);  
            
                                  return fileresult;  
                              } 
                    }
                ]
            }
         },
         
         //合并js文件
	    concat: {
             web_common_concat: {
	            options: {
	              banner: '/*!\n * <%= pkg.name %> - JS for Debug\n * @licence <%= pkg.name %> - v<%= pkg.version %> (<%= grunt.template.today("yyyy-mm-dd") %>)\n * http://blog.csdn.net/jennieji | Licence: MIT\n */\n'
	            },
	            // 源文件路径
	            src: [
	              'web/dist/common/js/**/*.js'
	            ],
	            // 运行任务后生成的目标文件
	            dest: 'web/dist/common/js/common_concat.js'
	        },
	        web_modules_concat: {
	            options: {
	              banner: '/*!\n * <%= pkg.name %> - JS for Debug\n * @licence <%= pkg.name %> - v<%= pkg.version %> (<%= grunt.template.today("yyyy-mm-dd") %>)\n * http://blog.csdn.net/jennieji | Licence: MIT\n */\n'
	            },
	            // 源文件路径
	            src: [
	              'web/dist/modules/**/*.js'
	            ],
	            // 运行任务后生成的目标文件
	            dest: 'web/dist/modules/modules_concat.js'
	        },
	        app_common_concat: {
	            options: {
	              banner: '/*!\n * <%= pkg.name %> - JS for Debug\n * @licence <%= pkg.name %> - v<%= pkg.version %> (<%= grunt.template.today("yyyy-mm-dd") %>)\n * http://blog.csdn.net/jennieji | Licence: MIT\n */\n'
	            },
	            // 源文件路径
	            src: [
	              'www/dist/common/js/**/*.js'
	            ],
	            // 运行任务后生成的目标文件
	            dest: 'www/dist/common/js/common_concat.js'
	        },
	        app_modules_concat: {
	            options: {
	              banner: '/*!\n * <%= pkg.name %> - JS for Debug\n * @licence <%= pkg.name %> - v<%= pkg.version %> (<%= grunt.template.today("yyyy-mm-dd") %>)\n * http://blog.csdn.net/jennieji | Licence: MIT\n */\n'
	            },
	            // 源文件路径
	            src: [
	              'www/dist/modules/**/*.js'
	            ],
	            // 运行任务后生成的目标文件
	            dest: 'www/dist/modules/modules_concat.js'
	        }
	    },

        //压缩css
        cssmin: {
            //文件头部输出信息
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n',
                //美化代码
                beautify: {
                    //中文ascii化，非常有用！防止中文乱码的神配置
                    ascii_only: true
                }
            },
            web_common_cssmin: {
                files: [
                    {
                        expand: true,
                        //相对路径
                        cwd: 'web/common/css/',
                        src: '*.css',
                        dest: 'web/dist/common/css/',
                        rename: function (dest, src) {  
                                var folder = src.substring(0, src.lastIndexOf('/'));  
                                var filename = src.substring(src.lastIndexOf('/'), src.length);  
                                //  var filename=src;  
                                filename = filename.substring(0, filename.lastIndexOf('.'));  
                                var fileresult=dest + folder + filename + '.css';  
                                grunt.log.writeln("现处理文件："+src+"  处理后文件："+fileresult);  
                                return fileresult;  
                            }
                    }
                ]
            },
            //合并web工程下common文件下的共用的css代码
            web_common_csscombine: {
                files: {
			      'web/dist/common/css/public.css': ['web/dist/common/css/*.css']
			    }
            },
            
            app_common_cssmin: {
                files: [
                    {
                        expand: true,
                        //相对路径
                        cwd: 'www/common/css/',
                        src: '*.css',
                        dest: 'www/dist/common/css/',
                        rename: function (dest, src) {  
                                var folder = src.substring(0, src.lastIndexOf('/'));  
                                var filename = src.substring(src.lastIndexOf('/'), src.length);  
                                //  var filename=src;  
                                filename = filename.substring(0, filename.lastIndexOf('.'));  
                                var fileresult=dest + folder + filename + '.css';  
                                grunt.log.writeln("现处理文件："+src+"  处理后文件："+fileresult);  
                                return fileresult;  
                            }
                    }
                ]
            }
        }

    });

    // 加载指定插件任务
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-cssmin');

    // 默认执行的任务
    grunt.registerTask('default', ['clean','uglify','concat', 'cssmin']);

};