package com.fiberlink.elasticsearch.batchmonitor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class ClusterStats {
	private String cluster_name;
	private String timestamp;
	private String status;
	private Indeces indices;
	private ClusterNodes nodes;

	public void printInfo() {
		System.out.println("------Cluster Stats------");
		System.out.println("Cluster Name: " + cluster_name);
		System.out.println("TimeStamp: " + timestamp);
		System.out.println("Cluster Status: " + status);
		indices.printInfo();
		nodes.printInfo();

	}

	public String getCluster_name() {
		return cluster_name;
	}

	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Indeces getIndices() {
		return indices;
	}

	public void setIndices(Indeces indices) {
		this.indices = indices;
	}

	public ClusterNodes getNodes() {
		return nodes;
	}

	public void setNodes(ClusterNodes nodes) {
		this.nodes = nodes;
	}

	public static class Indeces {
		private String count;
		private Shards shards;
		private Docs docs;
		private Store store;
		private FieldData fielddata;
		private FilterCache filter_cache;
		private IDCache id_cache;
		private Completion completion;
		private Segment segments;

		public void printInfo() {
			System.out.println("--Indeces--");
			System.out.println("Count: " + count);
			shards.printInfo();
			docs.printInfo();
			store.printInfo();
			fielddata.printInfo();
			filter_cache.printInfo();
			id_cache.printInfo();
			completion.printInfo();
			segments.printInfo();
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public Shards getShards() {
			return shards;
		}

		public void setShards(Shards shards) {
			this.shards = shards;
		}

		public Docs getDocs() {
			return docs;
		}

		public void setDocs(Docs docs) {
			this.docs = docs;
		}

		public Store getStore() {
			return store;
		}

		public void setStore(Store store) {
			this.store = store;
		}

		public FieldData getFielddata() {
			return fielddata;
		}

		public void setFielddata(FieldData fielddata) {
			this.fielddata = fielddata;
		}

		public FilterCache getFilter_cache() {
			return filter_cache;
		}

		public void setFilter_cache(FilterCache filter_cache) {
			this.filter_cache = filter_cache;
		}

		public IDCache getId_cache() {
			return id_cache;
		}

		public void setId_cache(IDCache id_cache) {
			this.id_cache = id_cache;
		}

		public Completion getCompletion() {
			return completion;
		}

		public void setCompletion(Completion completion) {
			this.completion = completion;
		}

		public Segment getSegments() {
			return segments;
		}

		public void setSegments(Segment segments) {
			this.segments = segments;
		}

		public static class FilterCache {
			private String memory_size;
			private String memory_size_in_bytes;
			private String evictions;

			public void printInfo() {
				System.out.println("--Filter Cache--");
				System.out.println("Memory Size: " + memory_size);
				System.out.println("Memory Size in Bytes: "
						+ memory_size_in_bytes);
				System.out.println("Eviction: " + evictions);
			}

			public String getMemory_size() {
				return memory_size;
			}

			public void setMemory_size(String memory_size) {
				this.memory_size = memory_size;
			}

			public String getMemory_size_in_bytes() {
				return memory_size_in_bytes;
			}

			public void setMemory_size_in_bytes(String memory_size_in_bytes) {
				this.memory_size_in_bytes = memory_size_in_bytes;
			}

			public String getEvictions() {
				return evictions;
			}

			public void setEvictions(String evictions) {
				this.evictions = evictions;
			}

		}

		public class IDCache {
			private String memory_size;
			private String memory_size_in_bytes;

			public void printInfo() {
				System.out.println("--ID Cache--");
				System.out.println("Memory Size: " + memory_size);
				System.out.println("Memory Size in Bytes: "
						+ memory_size_in_bytes);
			}

			public String getMemory_size() {
				return memory_size;
			}

			public void setMemory_size(String memory_size) {
				this.memory_size = memory_size;
			}

			public String getMemory_size_in_bytes() {
				return memory_size_in_bytes;
			}

			public void setMemory_size_in_bytes(String memory_size_in_bytes) {
				this.memory_size_in_bytes = memory_size_in_bytes;
			}

		}

		public static class Completion {
			private String size;
			private String size_in_bytes;

			public void printInfo() {
				System.out.println("--Completion--");
				System.out.println("Size: " + size);
				System.out.println("Size in Bytes: " + size_in_bytes);
			}

			public String getSize() {
				return size;
			}

			public void setSize(String size) {
				this.size = size;
			}

			public String getSize_in_bytes() {
				return size_in_bytes;
			}

			public void setSize_in_bytes(String size_in_bytes) {
				this.size_in_bytes = size_in_bytes;
			}

		}

		public static class Segment {
			private String count;
			private String memory;
			private String memory_in_bytes;

			public void printInfo() {
				System.out.println("--Segment--");
				System.out.println("Count: " + count);
				System.out.println("Memory: " + memory);
				System.out.println("Memory in Bytes: " + memory_in_bytes);
			}

			public String getCount() {
				return count;
			}

			public void setCount(String count) {
				this.count = count;
			}

			public String getMemory() {
				return memory;
			}

			public void setMemory(String memory) {
				this.memory = memory;
			}

			public String getMemory_in_bytes() {
				return memory_in_bytes;
			}

			public void setMemory_in_bytes(String memory_in_bytes) {
				this.memory_in_bytes = memory_in_bytes;
			}

		}

		public static class Docs {
			private String count;
			private String deleted;

			public void printInfo() {
				System.out.println("--Docs--");
				System.out.println("Count: " + count);
				System.out.println("Deleted: " + deleted);
			}

			public String getCount() {
				return count;
			}

			public void setCount(String count) {
				this.count = count;
			}

			public String getDeleted() {
				return deleted;
			}

			public void setDeleted(String deleted) {
				this.deleted = deleted;
			}
		}

		public static class Store {
			private String size;
			private String size_in_bytes;
			private String throttle_time;
			private String throttle_time_in_millis;

			public void printInfo() {
				System.out.println("--Store--");
				System.out.println("Size: " + size);
				System.out.println("Size in Bytes: " + size_in_bytes);
				System.out.println("Throttle Time: " + throttle_time);
				System.out.println("Throttle Time in Millis: "
						+ throttle_time_in_millis);
			}

			public String getSize() {
				return size;
			}

			public void setSize(String size) {
				this.size = size;
			}

			public String getSize_in_bytes() {
				return size_in_bytes;
			}

			public void setSize_in_bytes(String size_in_bytes) {
				this.size_in_bytes = size_in_bytes;
			}

			public String getThrottle_time() {
				return throttle_time;
			}

			public void setThrottle_time(String throttle_time) {
				this.throttle_time = throttle_time;
			}

			public String getThrottle_time_in_millis() {
				return throttle_time_in_millis;
			}

			public void setThrottle_time_in_millis(
					String throttle_time_in_millis) {
				this.throttle_time_in_millis = throttle_time_in_millis;
			}

		}

		public static class FieldData {
			private String memory_size;
			private String memory_size_in_bytes;
			private String evictions;

			public void printInfo() {
				System.out.println("--Field Data--");
				System.out.println("Memory Size: " + memory_size);
				System.out.println("Memory Size in Bytes: "
						+ memory_size_in_bytes);
				System.out.println("Eviction: " + evictions);
			}

			public String getMemory_size() {
				return memory_size;
			}

			public void setMemory_size(String memory_size) {
				this.memory_size = memory_size;
			}

			public String getMemory_size_in_bytes() {
				return memory_size_in_bytes;
			}

			public void setMemory_size_in_bytes(String memory_size_in_bytes) {
				this.memory_size_in_bytes = memory_size_in_bytes;
			}

			public String getEvictions() {
				return evictions;
			}

			public void setEvictions(String evictions) {
				this.evictions = evictions;
			}

		}

		public static class Shards {
			private String total;
			private String primaries;
			private String replication;
			private ShardIndex index;

			public void printInfo() {
				System.out.println("--Shard--");
				System.out.println("Total: " + total);
				System.out.println("Primaries: " + primaries);
				System.out.println("Replication: " + replication);
				index.printnfo();
			}

			public String getTotal() {
				return total;
			}

			public void setTotal(String total) {
				this.total = total;
			}

			public String getPrimaries() {
				return primaries;
			}

			public void setPrimaries(String primaries) {
				this.primaries = primaries;
			}

			public String getReplication() {
				return replication;
			}

			public void setReplication(String replication) {
				this.replication = replication;
			}

			public ShardIndex getIndex() {
				return index;
			}

			public void setIndex(ShardIndex index) {
				this.index = index;
			}

			public static class ShardIndex {
				private Map<String, ShardIndexType> allTypes = new HashMap<String, ShardIndexType>();
				private String typeName;

				public void printnfo() {
					for (Map.Entry<String, ShardIndexType> entry : allTypes
							.entrySet()) {
						typeName = entry.getKey();
						System.out.println("--" + typeName + "--");
						entry.getValue().printInfo();
					}
				}

				@JsonAnyGetter
				public Map<String, ShardIndexType> any() {
					return allTypes;
				}

				@JsonAnySetter
				public void set(String name, ShardIndexType value) {
					allTypes.put(name, value);
				}

				public String getTypeName() {
					return typeName;
				}

				public void setTypeName(String typeName) {
					this.typeName = typeName;
				}

				public static class ShardIndexType {
					private String min;
					private String max;
					private String avg;

					public void printInfo() {
						System.out.println("min: " + min);
						System.out.println("max: " + max);
						System.out.println("avg: " + avg);
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

					public String getAvg() {
						return avg;
					}

					public void setAvg(String avg) {
						this.avg = avg;
					}

				}
			}
		}

	}

	public static class ClusterNodes {
		private ClusterNodesCounts count;
		private List<String> versions = new ArrayList<String>();
		private ClusterNodesOS os;
		private ClusterNodesProcess process;
		private ClusterNodesJvm jvm;
		private ClusterNodesfs fs;
		private List<ClusterNodesPlugins> plugins = new ArrayList<ClusterNodesPlugins>();

		public void printInfo() {
			System.out.println("--Count--");
			count.printInfo();
			System.out.println("Version: ");
			for (String v : versions) {
				System.out.println(v);
			}
			os.printInfo();
			process.printInfo();
			jvm.printInfo();
			fs.printInfo();
			System.out.println("Plugins: ");
			for (ClusterNodesPlugins p : plugins) {
				p.printInfo();
			}
		}

		public ClusterNodesCounts getCount() {
			return count;
		}

		public void setCount(ClusterNodesCounts count) {
			this.count = count;
		}

		public List<String> getVersions() {
			return versions;
		}

		public void setVersions(List<String> versions) {
			this.versions = versions;
		}

		public ClusterNodesOS getOs() {
			return os;
		}

		public void setOs(ClusterNodesOS os) {
			this.os = os;
		}

		public ClusterNodesProcess getProcess() {
			return process;
		}

		public void setProcess(ClusterNodesProcess process) {
			this.process = process;
		}

		public ClusterNodesJvm getJvm() {
			return jvm;
		}

		public void setJvm(ClusterNodesJvm jvm) {
			this.jvm = jvm;
		}

		public ClusterNodesfs getFs() {
			return fs;
		}

		public void setFs(ClusterNodesfs fs) {
			this.fs = fs;
		}

		public List<ClusterNodesPlugins> getPlugins() {
			return plugins;
		}

		public void setPlugins(List<ClusterNodesPlugins> plugins) {
			this.plugins = plugins;
		}

		public static class ClusterNodesCounts {
			private String total;
			private String master_only;
			private String data_only;
			private String master_data;
			private String client;

			public void printInfo() {
				System.out.println("--Nodes count--");
				System.out.println("Total: " + total);
				System.out.println("Master Only: " + master_only);
				System.out.println("Data Only: " + data_only);
				System.out.println("Master Data: " + master_data);
				System.out.println("Client: " + client);
			}

			public String getTotal() {
				return total;
			}

			public void setTotal(String total) {
				this.total = total;
			}

			public String getMaster_only() {
				return master_only;
			}

			public void setMaster_only(String master_only) {
				this.master_only = master_only;
			}

			public String getData_only() {
				return data_only;
			}

			public void setData_only(String data_only) {
				this.data_only = data_only;
			}

			public String getMaster_data() {
				return master_data;
			}

			public void setMaster_data(String master_data) {
				this.master_data = master_data;
			}

			public String getClient() {
				return client;
			}

			public void setClient(String client) {
				this.client = client;
			}
		}

		public static class ClusterNodesOS {
			private String available_processors;
			private ClusterNodesOsMem mem;
			private List<ClusterNodesOSCpu> cpu = new ArrayList<ClusterNodesOSCpu>();

			public void printInfo() {
				System.out.println("Available Processors: "
						+ available_processors);
				mem.printInfo();
				System.out.println("CPU: ");
				for (ClusterNodesOSCpu c : cpu) {
					c.printInfo();
				}
			}

			public String getAvailable_processors() {
				return available_processors;
			}

			public void setAvailable_processors(String available_processors) {
				this.available_processors = available_processors;
			}

			public ClusterNodesOsMem getMem() {
				return mem;
			}

			public void setMem(ClusterNodesOsMem mem) {
				this.mem = mem;
			}

			public List<ClusterNodesOSCpu> getCpu() {
				return cpu;
			}

			public void setCpu(List<ClusterNodesOSCpu> cpu) {
				this.cpu = cpu;
			}

			public static class ClusterNodesOsMem {
				private String total;
				private String total_in_bytes;

				public void printInfo() {
					System.out.println("--mem--");
					System.out.println("Total: " + total);
					System.out.println("total in Bytes: " + total_in_bytes);
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

			public static class ClusterNodesOSCpu {
				private String vendor;
				private String model;
				private String mhz;
				private String total_cores;
				private String total_sockets;
				private String cores_per_socket;
				private String cache_size;
				private String cache_size_in_bytes;
				private String count;

				public void printInfo() {
					System.out.println("--CPU--");
					System.out.println("Vendor: " + vendor);
					System.out.println("Model: " + model);
					System.out.println("Mhz: " + mhz);
					System.out.println("Total Cores: " + total_cores);
					System.out.println("Total Sockets: " + total_sockets);
					System.out
							.println("Cores Per Sockets: " + cores_per_socket);
					System.out.println("Cache Size: " + cache_size);
					System.out.println("Cache Size in Bytes: "
							+ cache_size_in_bytes);
					System.out.println("Count: " + count);
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

				public String getCache_size() {
					return cache_size;
				}

				public void setCache_size(String cache_size) {
					this.cache_size = cache_size;
				}

				public String getCache_size_in_bytes() {
					return cache_size_in_bytes;
				}

				public void setCache_size_in_bytes(String cache_size_in_bytes) {
					this.cache_size_in_bytes = cache_size_in_bytes;
				}

				public String getCount() {
					return count;
				}

				public void setCount(String count) {
					this.count = count;
				}

			}
		}
		public static class ClusterNodesProcess {
			private ClusterNodesProcessCpu cpu;
			private String avg_open_file_descriptors;

			public void printInfo() {
				System.out.println("--Process--");
				System.out.println("Avg Open File Descriptor: "
						+ avg_open_file_descriptors);
				cpu.printInfo();
			}

			public ClusterNodesProcessCpu getCpu() {
				return cpu;
			}

			public void setCpu(ClusterNodesProcessCpu cpu) {
				this.cpu = cpu;
			}

			public String getAvg_open_file_descriptors() {
				return avg_open_file_descriptors;
			}

			public void setAvg_open_file_descriptors(
					String avg_open_file_descriptors) {
				this.avg_open_file_descriptors = avg_open_file_descriptors;
			}
			
			public static class ClusterNodesProcessCpu {
				private String percent;

				public String getPercent() {
					return percent;
				}

				public void setPercent(String percent) {
					this.percent = percent;
				}

				public void printInfo() {
					System.out.println("--CPU--");
					System.out.println("Percent: " + percent);
				}
			}
		}

		public static class ClusterNodesJvm {
			private String max_uptime;
			private String max_uptime_in_millis;
			private List<ClusterNodesJvmVersions> versions = new ArrayList<ClusterNodesJvmVersions>();
			private ClusterNodesJvmMem mem;
			private String threads;

			public void printInfo() {
				System.out.println("--JVM--");
				System.out.println("Max Uptime: " + max_uptime);
				System.out.println("Max Uptime in Millis: "
						+ max_uptime_in_millis);
				for (ClusterNodesJvmVersions v : versions) {
					v.printInfo();
				}
				mem.printInfo();
				System.out.println("Threads: " + threads);
			}

			public String getMax_uptime() {
				return max_uptime;
			}

			public void setMax_uptime(String max_uptime) {
				this.max_uptime = max_uptime;
			}

			public String getMax_uptime_in_millis() {
				return max_uptime_in_millis;
			}

			public void setMax_uptime_in_millis(String max_uptime_in_millis) {
				this.max_uptime_in_millis = max_uptime_in_millis;
			}

			public List<ClusterNodesJvmVersions> getVersions() {
				return versions;
			}

			public void setVersions(List<ClusterNodesJvmVersions> versions) {
				this.versions = versions;
			}

			public ClusterNodesJvmMem getMem() {
				return mem;
			}

			public void setMem(ClusterNodesJvmMem mem) {
				this.mem = mem;
			}

			public String getThreads() {
				return threads;
			}

			public void setThreads(String threads) {
				this.threads = threads;
			}

			public static class ClusterNodesJvmVersions {
				private String version;
				private String vm_name;
				private String vm_version;
				private String vm_vendor;
				private String count;

				public void printInfo() {
					System.out.println("--Version--");
					System.out.println("Version: " + version);
					System.out.println("Vm Name: " + vm_name);
					System.out.println("Vm Version: " + vm_version);
					System.out.println("Vm Vendor: " + vm_vendor);
					System.out.println("Count: " + count);
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

				public String getCount() {
					return count;
				}

				public void setCount(String count) {
					this.count = count;
				}
			}

			public static class ClusterNodesJvmMem {
				private String heap_used;
				private String heap_used_in_bytes;
				private String heap_max;
				private String heap_max_in_bytes;

				public void printInfo() {
					System.out.println("--Mem--");
					System.out.println("Heap Used: " + heap_used);
					System.out.println("Heap Used in Bytes: "
							+ heap_used_in_bytes);
					System.out.println("Heap Max: " + heap_max);
					System.out.println("Heap Max in Bytes: "
							+ heap_max_in_bytes);
				}

				public String getHeap_used() {
					return heap_used;
				}

				public void setHeap_used(String heap_used) {
					this.heap_used = heap_used;
				}

				public String getHeap_used_in_bytes() {
					return heap_used_in_bytes;
				}

				public void setHeap_used_in_bytes(String heap_used_in_bytes) {
					this.heap_used_in_bytes = heap_used_in_bytes;
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
			}

		}

		public static class ClusterNodesfs {
			private String total;
			private String total_in_bytes;
			private String free;
			private String free_in_bytes;
			private String available;
			private String available_in_bytes;

			public void printInfo() {
				System.out.println("--fs--");
				System.out.println("Total: " + total);
				System.out.println("Total in Bytes: " + total_in_bytes);
				System.out.println("Free: " + free);
				System.out.println("Free in Bytes: " + free_in_bytes);
				System.out.println("Available: " + available);
				System.out.println("Available in Bytes: " + available_in_bytes);
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

			public String getFree() {
				return free;
			}

			public void setFree(String free) {
				this.free = free;
			}

			public String getFree_in_bytes() {
				return free_in_bytes;
			}

			public void setFree_in_bytes(String free_in_bytes) {
				this.free_in_bytes = free_in_bytes;
			}

			public String getAvailable() {
				return available;
			}

			public void setAvailable(String available) {
				this.available = available;
			}

			public String getAvailable_in_bytes() {
				return available_in_bytes;
			}

			public void setAvailable_in_bytes(String available_in_bytes) {
				this.available_in_bytes = available_in_bytes;
			}

		}

		public static class ClusterNodesPlugins {
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
