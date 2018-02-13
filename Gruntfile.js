module.exports = function(grunt) {
	grunt.loadNpmTasks('grunt-shell');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.initConfig({
		shell: {
			greet: {
				command: function (greeting) {
					return 'echo ' + greeting;
				}
			},
			make: {
				command: 'mvn package -q',
			},
			clean: {
				command: 'mvn clean',
			},
			execute: {
                command: 'java -cp . -Djava.util.logging.config.file=src/main/resources/logging.properties -jar target/factorioPlanner-1.0-SNAPSHOT-jar-with-dependencies.jar &> out.log; cat out.log',
			},
			test: {
				command: 'echo "TEST COMPLETE"',
			},
		},
		watch: {
			scripts: {
				files: ['**/*.java'],
					     tasks: ['run'],
					     options: {
					     spawn: false,
					     },
					     },
					     },
	});
	grunt.registerTask('default', ['watch']);
	grunt.registerTask('run', ['shell:clean', 'shell:make', 'shell:execute', 'shell:test']);
	grunt.registerTask('exec', ['shell:execute', 'shell:test']);
	grunt.registerTask('test', ['shell:test']);
}
