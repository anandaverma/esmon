package com.fiberlink.elasticsearch.batchmonitor.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class NodeInfo {
	private String ok;
	private String cluster_name;
	private Nodes nodes;

	public Nodes getNodes() {
		return nodes;
	}

	public void setNodes(Nodes nodes) {
		this.nodes = nodes;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	public String getCluster_name() {
		return cluster_name;
	}

	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
	}

	public void printInfo() {
		System.out.println("------Nodes Information------");
		System.out.println("Ok: " + ok);
		System.out.println("Cluster Name: " + cluster_name);
		nodes.printnfo();
	}

	public static class Nodes {

		private Map<String, NodeObject> allNodes = new HashMap<String, NodeObject>();
		private String nodeID;

		public String getNodeID() {
			return nodeID;
		}

		public void setNodeID(String nodeID) {
			this.nodeID = nodeID;
		}

		@JsonAnyGetter
		public Map<String, NodeObject> any() {
			return allNodes;
		}

		@JsonAnySetter
		public void set(String name, NodeObject value) {
			allNodes.put(name, value);
		}

		public void printnfo() {
			for (Map.Entry<String, NodeObject> entry : allNodes.entrySet()) {
				nodeID = entry.getKey();
				System.out.println("--" + nodeID + "--");
				System.out.println("Node ID: " + nodeID);
				entry.getValue().printInfo();
			}
		}
		//@JsonIgnoreProperties({"attributes"})
		public static class NodeObject {
			private String name;
			private String transport_address;
			private String hostname;
			private String version;
			private String http_address;
			private Attributes attributes;
			private Settings settings;
			private Os os;
			private ESProcess process;
			private Jvm jvm;
			private ThreadPool thread_pool;
			private Network network;
			private Transport transport;
			private Http http;
			private List<Plugins> plugins;

			public void printInfo() {
				System.out.println("Node Name: " + name);
				System.out.println("Transport Address: " + transport_address);
				System.out.println("Host Name: " + hostname);
				System.out.println("Version:" + version);
				System.out.println("Http Address: " + http_address);

				// attributes.printInfo();
				settings.printInfo();
				os.printInfo();
				process.printInfo();
				jvm.printInfo();
				thread_pool.printInfo();
				network.printInfo();
				transport.printInfo();
				// http.printInfo();

				for (Plugins p : plugins) {
					p.printInfo();
				}

			}

			public Attributes getAttributes() {
				return attributes;
			}

			public void setAttributes(Attributes attributes) {
				this.attributes = attributes;
			}

			public Settings getSettings() {
				return settings;
			}

			public void setSettings(Settings settings) {
				this.settings = settings;
			}

			public ESProcess getProcess() {
				return process;
			}

			public void setPs(ESProcess process) {
				this.process = process;
			}

			public Jvm getJvm() {
				return jvm;
			}

			public void setJvm(Jvm jvm) {
				this.jvm = jvm;
			}

			public ThreadPool getThread_pool() {
				return thread_pool;
			}

			public void setThread_pool(ThreadPool thread_pool) {
				this.thread_pool = thread_pool;
			}

			public void setProcess(ESProcess process) {
				this.process = process;
			}

			public Network getNetwork() {
				return network;
			}

			public void setNetwork(Network network) {
				this.network = network;
			}

			public Transport getTransport() {
				return transport;
			}

			public void setTransport(Transport transport) {
				this.transport = transport;
			}

			public Http getHttp() {
				return http;
			}

			public void setHttp(Http http) {
				this.http = http;
			}

			public List<Plugins> getPlugins() {
				return plugins;
			}

			public void setPlugins(List<Plugins> plugins) {
				this.plugins = plugins;
			}

			public String getVersion() {
				return version;
			}

			public void setVersion(String version) {
				this.version = version;
			}

			public String getHttp_address() {
				return http_address;
			}

			public void setHttp_address(String http_address) {
				this.http_address = http_address;
			}

			public Os getOs() {
				return os;
			}

			public void setOs(Os os) {
				this.os = os;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getTransport_address() {
				return transport_address;
			}

			public void setTransport_address(String transport_address) {
				this.transport_address = transport_address;
			}

			public String getHostname() {
				return hostname;
			}

			public void setHostname(String hostname) {
				this.hostname = hostname;
			}

			public static class Attributes {
				String client;
				String data;
				String master;

				public void printInfo() {
					System.out.println("--Attributes--");
					System.out.println("Client: " + client);
					System.out.println("Data: " + data);
					System.out.println("Master: " + master);
				}

				public String getMaster() {
					return master;
				}

				public void setMaster(String master) {
					this.master = master;
				}

				public String getClient() {
					return client;
				}

				public void setClient(String client) {
					this.client = client;
				}

				public String getData() {
					return data;
				}

				public void setData(String data) {
					this.data = data;
				}

			}

			public static class Settings {
				private Map<String, String> values = new HashMap<String, String>();

				@JsonAnyGetter
				public Map<String, String> any() {
					return values;
				}

				@JsonAnySetter
				public void set(String name, String value) {
					values.put(name, value);
				}

				public void printInfo() {
					System.out.println("--settings--");
					for (Map.Entry<String, String> s : values.entrySet())
						System.out.println(s.getKey() + ":" + s.getValue());
				}

			}

			public static class Os {
				private String refresh_interval;
				private String available_processors;
				private Cpu cpu;
				private Mem mem;
				private Swap swap;

				public void printInfo() {
					System.out.println("--OS--");
					System.out.println("Refresh Interval: " + refresh_interval);
					System.out.println("available_processes: "
							+ available_processors);
					// cpu.printInfo();
					// mem.printInfo();
					// swap.printInfo();
				}

				public String getRefresh_interval() {
					return refresh_interval;
				}

				public void setRefresh_interval(String refresh_interval) {
					this.refresh_interval = refresh_interval;
				}

				public String getAvailable_processors() {
					return available_processors;
				}

				public void setAvailable_processors(String available_processors) {
					this.available_processors = available_processors;
				}

				public Cpu getCpu() {
					return cpu;
				}

				public void setCpu(Cpu cpu) {
					this.cpu = cpu;
				}

				public Mem getMem() {
					return mem;
				}

				public void setMem(Mem mem) {
					this.mem = mem;
				}

				public Swap getSwap() {
					return swap;
				}

				public void setSwap(Swap swap) {
					this.swap = swap;
				}

				public static class Swap {
					private String total;
					private String total_in_bytes;

					public void printInfo() {
						System.out.println("--Swap--");
						System.out.println("Total Swap: " + total);
						System.out.println("Total Swap in Bytes: "
								+ total_in_bytes);

					}

					public String getTotal() {
						return total;
					}

					public void setTotal(String total) {
						this.total = total;
					}

					public String getTotal_in_bytes() {
						return total_in_bytes;
					}

					public void setTotal_in_bytes(String total_in_bytes) {
						this.total_in_bytes = total_in_bytes;
					}
				}

				public static class Cpu {
					private String vendor;
					private String model;
					private String mhz;
					private String total_cores;
					private String total_sockets;
					private String cores_per_socket;
					private String cache_size;
					private String cache_size_in_bytes;

					public String getCache_size() {
						return cache_size;
					}

					public void printInfo() {
						System.out.println("--CPU--");
						System.out.println("Vendor: " + vendor);
						System.out.println("Model: " + model);
						System.out.println("Mhz: " + mhz);
						System.out.println("Total Cores: " + total_cores);
						System.out.println("Total Sockets: " + total_sockets);
						System.out.println("cores_per_socket: "
								+ cores_per_socket);
						System.out.println("Cache Size: " + cache_size);
						System.out.println("Cache Size in Bytes: "
								+ cache_size_in_bytes);

					}

					public void setCache_size(String cache_size) {
						this.cache_size = cache_size;
					}

					public String getVendor() {
						return vendor;
					}

					public void setVendor(String vendor) {
						this.vendor = vendor;
					}

					public String getModel() {
						return model;
					}

					public void setModel(String model) {
						this.model = model;
					}

					public String getMhz() {
						return mhz;
					}

					public void setMhz(String mhz) {
						this.mhz = mhz;
					}

					public String getTotal_cores() {
						return total_cores;
					}

					public void setTotal_cores(String total_cores) {
						this.total_cores = total_cores;
					}

					public String getTotal_sockets() {
						return total_sockets;
					}

					public void setTotal_sockets(String total_sockets) {
						this.total_sockets = total_sockets;
					}

					public String getCores_per_socket() {
						return cores_per_socket;
					}

					public void setCores_per_socket(String cores_per_socket) {
						this.cores_per_socket = cores_per_socket;
					}

					public String getCache_size_in_bytes() {
						return cache_size_in_bytes;
					}

					public void setCache_size_in_bytes(
							String cache_size_in_bytes) {
						this.cache_size_in_bytes = cache_size_in_bytes;
					}
				}

				public static class Mem {
					private String total;
					private String total_in_bytes;

					public String getTotal() {
						return total;
					}

					public void printInfo() {
						System.out.println("--Memory--");
						System.out.println("Total Memory: " + total);
						System.out.println("Total Memory in Bytes: "
								+ total_in_bytes);
					}

					public void setTotal(String total) {
						this.total = total;
					}

					public String getTotal_in_bytes() {
						return total_in_bytes;
					}

					public void setTotal_in_bytes(String total_in_bytes) {
						this.total_in_bytes = total_in_bytes;
					}

				}

			}

			public static class Jvm {
				private String pid;
				private String version;
				private String vm_name;
				private String vm_version;
				private String vm_vendor;
				private String start_time;
				private JvmMem mem;

				public void printInfo() {
					System.out.println("--JVM--");
					System.out.println("PID: " + pid);
					System.out.println("Version: " + version);
					System.out.println("Vm Name: " + vm_name);
					System.out.println("Vm Version: " + vm_version);
					System.out.println("Vm Vendor: " + vm_vendor);
					System.out.println("Start Time: " + start_time);
					mem.printInfo();

				}

				public JvmMem getMem() {
					return mem;
				}

				public void setMem(JvmMem mem) {
					this.mem = mem;
				}

				public String getPid() {
					return pid;
				}

				public void setPid(String pid) {
					this.pid = pid;
				}

				public String getVersion() {
					return version;
				}

				public void setVersion(String version) {
					this.version = version;
				}

				public String getVm_name() {
					return vm_name;
				}

				public void setVm_name(String vm_name) {
					this.vm_name = vm_name;
				}

				public String getVm_version() {
					return vm_version;
				}

				public void setVm_version(String vm_version) {
					this.vm_version = vm_version;
				}

				public String getVm_vendor() {
					return vm_vendor;
				}

				public void setVm_vendor(String vm_vendor) {
					this.vm_vendor = vm_vendor;
				}

				public String getStart_time() {
					return start_time;
				}

				public void setStart_time(String start_time) {
					this.start_time = start_time;
				}

				public static class JvmMem {
					private String heap_init;
					private String heap_init_in_bytes;
					private String heap_max;
					private String heap_max_in_bytes;
					private String non_heap_init;
					private String non_heap_init_in_bytes;
					private String non_heap_max;
					private String non_heap_max_in_bytes;
					private String direct_max;
					private String direct_max_in_bytes;

					public void printInfo() {
						System.out.println("--Jvm Memory--");
						System.out.println("Heap Init: " + heap_init);
						System.out.println("Heap Init in bytes: "
								+ heap_init_in_bytes);
						System.out.println("Heap Max: " + heap_max);
						System.out.println("Non Heap Init: " + non_heap_init);
						System.out.println("Non Heap Init in Bytes: "
								+ non_heap_init_in_bytes);
						System.out.println("Non Heap Max: " + non_heap_max);
						System.out.println("Non Heap Max in Bytes: "
								+ non_heap_max_in_bytes);
						System.out.println("Direct Max: " + direct_max);
						System.out.println("Direct Max in Bytes: "
								+ direct_max_in_bytes);
					}

					public String getHeap_init() {
						return heap_init;
					}

					public void setHeap_init(String heap_init) {
						this.heap_init = heap_init;
					}

					public String getHeap_init_in_bytes() {
						return heap_init_in_bytes;
					}

					public void setHeap_init_in_bytes(String heap_init_in_bytes) {
						this.heap_init_in_bytes = heap_init_in_bytes;
					}

					public String getHeap_max() {
						return heap_max;
					}

					public void setHeap_max(String heap_max) {
						this.heap_max = heap_max;
					}

					public String getHeap_max_in_bytes() {
						return heap_max_in_bytes;
					}

					public void setHeap_max_in_bytes(String heap_max_in_bytes) {
						this.heap_max_in_bytes = heap_max_in_bytes;
					}

					public String getNon_heap_init() {
						return non_heap_init;
					}

					public void setNon_heap_init(String non_heap_init) {
						this.non_heap_init = non_heap_init;
					}

					public String getNon_heap_init_in_bytes() {
						return non_heap_init_in_bytes;
					}

					public void setNon_heap_init_in_bytes(
							String non_heap_init_in_bytes) {
						this.non_heap_init_in_bytes = non_heap_init_in_bytes;
					}

					public String getNon_heap_max() {
						return non_heap_max;
					}

					public void setNon_heap_max(String non_heap_max) {
						this.non_heap_max = non_heap_max;
					}

					public String getNon_heap_max_in_bytes() {
						return non_heap_max_in_bytes;
					}

					public void setNon_heap_max_in_bytes(
							String non_heap_max_in_bytes) {
						this.non_heap_max_in_bytes = non_heap_max_in_bytes;
					}

					public String getDirect_max() {
						return direct_max;
					}

					public void setDirect_max(String direct_max) {
						this.direct_max = direct_max;
					}

					public String getDirect_max_in_bytes() {
						return direct_max_in_bytes;
					}

					public void setDirect_max_in_bytes(
							String direct_max_in_bytes) {
						this.direct_max_in_bytes = direct_max_in_bytes;
					}
				}

			}

			public static class ESProcess {
				private String refresh_interval;
				private String id;
				private String max_file_descriptors;
				private String mlockall;

				public void printInfo() {
					System.out.println("--Process--");
					System.out.println("Refresh Interval: " + refresh_interval);
					System.out.println("ProcessID: " + id);
					System.out.println("Max File Descriptor: "
							+ max_file_descriptors);
					System.out.println("Mlockall: " + mlockall);
				}

				public String getRefresh_interval() {
					return refresh_interval;
				}

				public void setRefresh_interval(String refresh_interval) {
					this.refresh_interval = refresh_interval;
				}

				public String getId() {
					return id;
				}

				public void setId(String id) {
					this.id = id;
				}

				public String getMax_file_descriptors() {
					return max_file_descriptors;
				}

				public void setMax_file_descriptors(String max_file_descriptors) {
					this.max_file_descriptors = max_file_descriptors;
				}

				public String getMlockall() {
					return mlockall;
				}

				public void setMlockall(String mlockall) {
					this.mlockall = mlockall;
				}

			}

			public static class ThreadPool {
				private Generic generic;
				private Index index;
				private Get get;
				private Snapshot snapshot;
				private Merge merge;
				private Suggest suggest;
				private Bulk bulk;
				private Optimize optimize;
				private Warmer warmer;
				private Flush flush;
				private Search search;
				private Percolate percolate;
				private Management management;
				private Refresh refresh;

				public Generic getGeneric() {
					return generic;
				}

				public void setGeneric(Generic generic) {
					this.generic = generic;
				}

				public Index getIndex() {
					return index;
				}

				public void setIndex(Index index) {
					this.index = index;
				}

				public Get getGet() {
					return get;
				}

				public void setGet(Get get) {
					this.get = get;
				}

				public Snapshot getSnapshot() {
					return snapshot;
				}

				public void setSnapshot(Snapshot snapshot) {
					this.snapshot = snapshot;
				}

				public Merge getMerge() {
					return merge;
				}

				public void setMerge(Merge merge) {
					this.merge = merge;
				}

				public Suggest getSuggest() {
					return suggest;
				}

				public void setSuggest(Suggest suggest) {
					this.suggest = suggest;
				}

				public Bulk getBulk() {
					return bulk;
				}

				public void setBulk(Bulk bulk) {
					this.bulk = bulk;
				}

				public Optimize getOptimize() {
					return optimize;
				}

				public void setOptimize(Optimize optimize) {
					this.optimize = optimize;
				}

				public Warmer getWarmer() {
					return warmer;
				}

				public void setWarmer(Warmer warmer) {
					this.warmer = warmer;
				}

				public Flush getFlush() {
					return flush;
				}

				public void setFlush(Flush flush) {
					this.flush = flush;
				}

				public Search getSearch() {
					return search;
				}

				public void setSearch(Search search) {
					this.search = search;
				}

				public Percolate getPercolate() {
					return percolate;
				}

				public void setPercolate(Percolate percolate) {
					this.percolate = percolate;
				}

				public Management getManagement() {
					return management;
				}

				public void setManagement(Management management) {
					this.management = management;
				}

				public Refresh getRefresh() {
					return refresh;
				}

				public void setRefresh(Refresh refresh) {
					this.refresh = refresh;
				}

				public void printInfo() {
					System.out.println("--ThreadPool");
					generic.printInfo();
					index.printInfo();
					get.printInfo();
					snapshot.printInfo();
					merge.printInfo();
					suggest.printInfo();
					bulk.printInfo();
					optimize.printInfo();
					warmer.printInfo();
					flush.printInfo();
					search.printInfo();
					percolate.printInfo();
					management.printInfo();
					refresh.printInfo();
				}

				public static class Generic {
					private String type;
					private String keep_alive;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getKeep_alive() {
						return keep_alive;
					}

					public void setKeep_alive(String keep_alive) {
						this.keep_alive = keep_alive;
					}

					void printInfo() {
						System.out.println("--Generic--");
						System.out.println("Generic Type: " + type);
						System.out.println("Generic Keep Alive" + keep_alive);
					}
				}

				public static class Index {
					private String type;
					private String min;
					private String max;
					private String queue_size;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getMin() {
						return min;
					}

					public void setMin(String min) {
						this.min = min;
					}

					public String getMax() {
						return max;
					}

					public void setMax(String max) {
						this.max = max;
					}

					public String getQueue_size() {
						return queue_size;
					}

					public void setQueue_size(String queue_size) {
						this.queue_size = queue_size;
					}

					public void printInfo() {
						System.out.println("--Index--");
						System.out.println("Index type: " + type);
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
						System.out.println("Queue Size: " + queue_size);
					}
				}

				public static class Get {
					private String type;
					private String min;
					private String max;
					private String queue_size;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getMin() {
						return min;
					}

					public void setMin(String min) {
						this.min = min;
					}

					public String getMax() {
						return max;
					}

					public void setMax(String max) {
						this.max = max;
					}

					public String getQueue_size() {
						return queue_size;
					}

					public void setQueue_size(String queue_size) {
						this.queue_size = queue_size;
					}

					public void printInfo() {
						System.out.println("--Get--");
						System.out.println("Index type: " + type);
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
						System.out.println("Queue Size: " + queue_size);
					}
				}

				public static class Snapshot {
					private String type;
					private String min;
					private String max;
					private String keep_alive;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getMin() {
						return min;
					}

					public void setMin(String min) {
						this.min = min;
					}

					public String getMax() {
						return max;
					}

					public void setMax(String max) {
						this.max = max;
					}

					public String getKeep_alive() {
						return keep_alive;
					}

					public void setKeep_alive(String keep_alive) {
						this.keep_alive = keep_alive;
					}

					public void printInfo() {
						System.out.println("--SnapShot--");
						System.out.println("Index type: " + type);
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
						System.out.println("Keep Alive: " + keep_alive);
					}
				}

				public static class Merge {
					private String type;
					private String min;
					private String max;
					private String keep_alive;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getMin() {
						return min;
					}

					public void setMin(String min) {
						this.min = min;
					}

					public String getMax() {
						return max;
					}

					public void setMax(String max) {
						this.max = max;
					}

					public String getKeep_alive() {
						return keep_alive;
					}

					public void setKeep_alive(String keep_alive) {
						this.keep_alive = keep_alive;
					}

					public void printInfo() {
						System.out.println("--Merge--");
						System.out.println("Index type: " + type);
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
						System.out.println("Keep Alive: " + keep_alive);
					}
				}

				public static class Suggest {
					private String type;
					private String min;
					private String max;
					private String queue_size;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getMin() {
						return min;
					}

					public void setMin(String min) {
						this.min = min;
					}

					public String getMax() {
						return max;
					}

					public void setMax(String max) {
						this.max = max;
					}

					public String getQueue_size() {
						return queue_size;
					}

					public void setQueue_size(String queue_size) {
						this.queue_size = queue_size;
					}

					public void printInfo() {
						System.out.println("--Suggest--");
						System.out.println("Index type: " + type);
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
						System.out.println("Queue Size: " + queue_size);
					}
				}

				public static class Bulk {
					private String type;
					private String min;
					private String max;
					private String queue_size;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getMin() {
						return min;
					}

					public void setMin(String min) {
						this.min = min;
					}

					public String getMax() {
						return max;
					}

					public void setMax(String max) {
						this.max = max;
					}

					public String getQueue_size() {
						return queue_size;
					}

					public void setQueue_size(String queue_size) {
						this.queue_size = queue_size;
					}

					public void printInfo() {
						System.out.println("--Bulk--");
						System.out.println("Index type: " + type);
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
						System.out.println("Queue Size: " + queue_size);
					}
				}

				public static class Optimize {
					private String type;
					private String min;
					private String max;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getMin() {
						return min;
					}

					public void setMin(String min) {
						this.min = min;
					}

					public String getMax() {
						return max;
					}

					public void setMax(String max) {
						this.max = max;
					}

					public void printInfo() {
						System.out.println("--Optimize--");
						System.out.println("Index type: " + type);
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
					}
				}

				public static class Warmer {
					private String type;
					private String min;
					private String max;
					private String keep_alive;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getMin() {
						return min;
					}

					public void setMin(String min) {
						this.min = min;
					}

					public String getMax() {
						return max;
					}

					public void setMax(String max) {
						this.max = max;
					}

					public String getKeep_alive() {
						return keep_alive;
					}

					public void setKeep_alive(String keep_alive) {
						this.keep_alive = keep_alive;
					}

					public void printInfo() {
						System.out.println("--Warmer--");
						System.out.println("Index type: " + type);
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
						System.out.println("Keep Alive: " + keep_alive);
					}
				}

				public static class Flush {
					private String type;
					private String min;
					private String max;
					private String keep_alive;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getMin() {
						return min;
					}

					public void setMin(String min) {
						this.min = min;
					}

					public String getMax() {
						return max;
					}

					public void setMax(String max) {
						this.max = max;
					}

					public String getKeep_alive() {
						return keep_alive;
					}

					public void setKeep_alive(String keep_alive) {
						this.keep_alive = keep_alive;
					}

					public void printInfo() {
						System.out.println("--Flush--");
						System.out.println("Index type: " + type);
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
						System.out.println("Keep Alive: " + keep_alive);
					}
				}

				public static class Search {
					private String type;
					private String min;
					private String max;
					private String queue_size;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getMin() {
						return min;
					}

					public void setMin(String min) {
						this.min = min;
					}

					public String getMax() {
						return max;
					}

					public void setMax(String max) {
						this.max = max;
					}

					public String getQueue_size() {
						return queue_size;
					}

					public void setQueue_size(String queue_size) {
						this.queue_size = queue_size;
					}

					public void printInfo() {
						System.out.println("--Search--");
						System.out.println("Index type: " + type);
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
						System.out.println("Queue Size: " + queue_size);
					}
				}

				public static class Percolate {
					private String type;
					private String min;
					private String max;
					private String queue_size;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getMin() {
						return min;
					}

					public void setMin(String min) {
						this.min = min;
					}

					public String getMax() {
						return max;
					}

					public void setMax(String max) {
						this.max = max;
					}

					public String getQueue_size() {
						return queue_size;
					}

					public void setQueue_size(String queue_size) {
						this.queue_size = queue_size;
					}

					public void printInfo() {
						System.out.println("--Percolate--");
						System.out.println("Index type: " + type);
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
						System.out.println("Queue Size: " + queue_size);
					}
				}

				public static class Management {
					private String type;
					private String min;
					private String max;
					private String keep_alive;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getMin() {
						return min;
					}

					public void setMin(String min) {
						this.min = min;
					}

					public String getMax() {
						return max;
					}

					public void setMax(String max) {
						this.max = max;
					}

					public String getKeep_alive() {
						return keep_alive;
					}

					public void setKeep_alive(String keep_alive) {
						this.keep_alive = keep_alive;
					}

					public void printInfo() {
						System.out.println("--Management--");
						System.out.println("Index type: " + type);
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
						System.out.println("Keep Alive: " + keep_alive);
					}
				}

				public static class Refresh {
					private String type;
					private String min;
					private String max;
					private String keep_alive;

					public String getType() {
						return type;
					}

					public void setType(String type) {
						this.type = type;
					}

					public String getMin() {
						return min;
					}

					public void setMin(String min) {
						this.min = min;
					}

					public String getMax() {
						return max;
					}

					public void setMax(String max) {
						this.max = max;
					}

					public String getKeep_alive() {
						return keep_alive;
					}

					public void setKeep_alive(String keep_alive) {
						this.keep_alive = keep_alive;
					}

					public void printInfo() {
						System.out.println("--Refresh--");
						System.out.println("Index type: " + type);
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
						System.out.println("Keep Alive: " + keep_alive);
					}
				}

			}

			public static class Network {
				private String refresh_interval;
				private PrimaryInterface primary_interface;

				public String getRefresh_interval() {
					return refresh_interval;
				}

				public void setRefresh_interval(String refresh_interval) {
					this.refresh_interval = refresh_interval;
				}

				public PrimaryInterface getPrimary_interface() {
					return primary_interface;
				}

				public void setPrimary_interface(
						PrimaryInterface primary_interface) {
					this.primary_interface = primary_interface;
				}

				public void printInfo() {
					System.out.println("--Network--");
					System.out.println("Refesh Interval: " + refresh_interval);
					// primary_interface.printInfo();
				}

				public static class PrimaryInterface {
					private String address;
					private String name;
					private String mac_address;

					public void printInfo() {
						System.out.println("--Primary Interface--");
						System.out.println("Address: " + address);
						System.out.println("Name: " + name);
						System.out.println("Mac Address: " + mac_address);
					}

					public String getAddress() {
						return address;
					}

					public void setAddress(String address) {
						this.address = address;
					}

					public String getName() {
						return name;
					}

					public void setName(String name) {
						this.name = name;
					}

					public String getMac_address() {
						return mac_address;
					}

					public void setMac_address(String mac_address) {
						this.mac_address = mac_address;
					}

				}
			}

			public static class Transport {
				private String bound_address;
				private String publish_address;

				public void printInfo() {
					System.out.println("--Transport--");
					System.out.println("Bound Address: " + bound_address);
					System.out.println("Pubish Address: " + publish_address);
				}

				public String getBound_address() {
					return bound_address;
				}

				public void setBound_address(String bound_address) {
					this.bound_address = bound_address;
				}

				public String getPublish_address() {
					return publish_address;
				}

				public void setPublish_address(String publish_address) {
					this.publish_address = publish_address;
				}

			}

			public static class Http {
				private String bound_address;
				private String publish_address;
				private String max_content_length;
				private String max_content_length_in_bytes;

				public void printInfo() {
					System.out.println("--HTTP--");
					System.out.println("Bound Address: " + bound_address);
					System.out.println("Publish Address: " + publish_address);
					System.out.println("Max Content Length: "
							+ max_content_length);
					System.out.println("Max Content Length in Bytes: "
							+ max_content_length_in_bytes);
				}

				public String getBound_address() {
					return bound_address;
				}

				public void setBound_address(String bound_address) {
					this.bound_address = bound_address;
				}

				public String getPublish_address() {
					return publish_address;
				}

				public void setPublish_address(String publish_address) {
					this.publish_address = publish_address;
				}

				public String getMax_content_length() {
					return max_content_length;
				}

				public void setMax_content_length(String max_content_length) {
					this.max_content_length = max_content_length;
				}

				public String getMax_content_length_in_bytes() {
					return max_content_length_in_bytes;
				}

				public void setMax_content_length_in_bytes(
						String max_content_length_in_bytes) {
					this.max_content_length_in_bytes = max_content_length_in_bytes;
				}

			}

			public static class Plugins {
				private String name;
				private String description;
				private String url;
				private String jvm;
				private String site;

				public String getName() {
					return name;
				}

				public void setName(String name) {
					this.name = name;
				}

				public String getDescription() {
					return description;
				}

				public void setDescription(String description) {
					this.description = description;
				}

				public String getUrl() {
					return url;
				}

				public void setUrl(String url) {
					this.url = url;
				}

				public String getJvm() {
					return jvm;
				}

				public void setJvm(String jvm) {
					this.jvm = jvm;
				}

				public String getSite() {
					return site;
				}

				public void setSite(String site) {
					this.site = site;
				}

				public void printInfo() {
					System.out.println("--Plugins--");
					System.out.println("Name: " + name);
					System.out.println("Description: " + description);
					System.out.println("Url: " + url);
					System.out.println("Jvm: " + jvm);
					System.out.println("Site: " + site);
				}
			}

		}
	}

}
