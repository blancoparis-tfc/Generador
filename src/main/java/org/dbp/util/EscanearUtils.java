package org.dbp.util;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class EscanearUtils {
	
	private final ClassLoader classLoader;

	public static EscanearUtils instancia(){
		return new EscanearUtils();
	}
	
	public EscanearUtils() {
		super();
		this.classLoader=Thread.currentThread().getContextClassLoader();
	}
	
	public List<Class> escanearPaquete(String paquete) throws IOException, URISyntaxException, ClassNotFoundException{
		final List<Class> valdev = new ArrayList<Class>();
		List<URL> urls = recursosPaquete(paquete);
		for(URL url:urls){
			Path pathPaquete=resourceToPath(url);
			try(Stream<Path> ficheros=Files.find(pathPaquete, 1, (matcher,options)->options.isRegularFile())){
				List<String> strClases=ficheros
						.map(path->construirPathClase(paquete, path))
						.collect(toList());
				for(String clase:strClases){
					valdev.add(Class.forName(clase));
				}
			}
		}
		return valdev;
	}

	private Path resourceToPath(URL resource)
			throws IOException,
			       URISyntaxException {
			    Objects.requireNonNull(resource, "Resource URL cannot be null");
			    URI uri = resource.toURI();
			    String scheme = uri.getScheme();
			    if (scheme.equals("file")) {
			        return Paths.get(uri);
			    }
			    if (!scheme.equals("jar")) {
			        throw new IllegalArgumentException("Cannot convert to Path: " + uri);
			    }
			    String strUri = uri.toString();
			    int separador = strUri.indexOf("!/");
			    URI fileURI = URI.create(strUri.substring(0, separador));
			    FileSystem fileSystemJar = FileSystems.newFileSystem(fileURI,Collections.<String, Object>emptyMap());
			    return fileSystemJar.getPath(strUri.substring(separador + 2)); 
			}
	
	private String construirPathClase(String paquete, Path path) {
		return paquete.concat(".").concat(path.getFileName().toString().replace(".class", ""));
	}
	
	private List<URL> recursosPaquete(String paquete) throws IOException{
		return escanearRecursos(resourceName(paquete));
	}
	
	private List<URL> escanearRecursos(String recurso) throws IOException{
		List<URL> valdev = new ArrayList<URL>();
		Enumeration<URL> urls =this.classLoader.getResources(recurso);
		while(urls.hasMoreElements()){
			valdev.add(urls.nextElement());
		}
		return valdev;
	}
	
    private String resourceName(final String name) {
        if (name != null) {
            String resourceName = name.replace(".", "/");
            resourceName = resourceName.replace("\\", "/");
            if (resourceName.startsWith("/")) {
                resourceName = resourceName.substring(1);
            }
            return resourceName;
        }
        return null;
    }
	
}
