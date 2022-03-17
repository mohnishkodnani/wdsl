{
	inputs = {
    nixpkgs.url = github:nixos/nixpkgs/nixpkgs-unstable;
		flake-utils.url = github:numtide/flake-utils;
	};
	outputs = { self, nixpkgs, flake-utils, ...} @ inputs:
	  flake-utils.lib.eachDefaultSystem(system: 
			let 
				pkgs = nixpkgs.legacyPackages.${system};
				jdk = pkgs.jdk17_headless;
			in
			  rec {
				  devShell = pkgs.mkShell {
					name = "scala-dev-shell";
					buildInputs = [
					jdk
					pkgs.coursier
					pkgs.sbt
					];
					shellHook = ''
					JAVA_HOME = "${jdk}"
					'';
					};
				}
		);	
}
