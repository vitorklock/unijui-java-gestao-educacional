package main.bootstrap;

public final class AppContext {
    private final Repositories repos;
    private final Services services;

    public AppContext(Repositories repos, Services services) {
        this.repos = repos;
        this.services = services;
    }

    public Repositories repos() { return repos; }
    public Services services() { return services; }
}
