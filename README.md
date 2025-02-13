# ADX 数据项目

ADX 数据处理项目旨在为广告交易平台提供高效的数据处理解决方案，支持实时流处理、批处理和数据湖架构，帮助平台处理来自不同数据源（如广告点击数据、用户行为数据等）的海量数据，提供精准的广告投放和效果分析。

## 项目架构

该项目包含多个模块，每个模块负责不同的功能部分，整体架构如下：

- **adx_platform_ads**：处理广告投放数据，包括广告的分发、点击和展示数据。
- **adx_platform_common**：定义项目中通用的工具和库，如日志、数据格式转换等。
- **adx_platform_dim**：处理维度数据，如用户、广告位、地理位置等信息的维护。
- **adx_platform_dwd**：数据仓库层，处理并保存精细化的广告数据。
- **adx_platform_dws**：数据汇总层，提供汇总数据用于报表和分析。
- **adx_platform_ods**：原始数据层，处理从外部系统获取的原始数据。
- **adx_platform_udf**：用户自定义函数模块，支持自定义计算和转换。

## 特性

- **实时数据处理**：基于 Apache Flink 进行实时流处理，支持低延迟、高吞吐量的数据处理。
- **批量数据处理**：使用 Apache Spark 进行批量处理和数据分析。
- **数据湖架构**：使用 Hudi 和其他数据存储技术进行数据湖的构建和管理。
- **高可扩展性**：模块化设计，支持自定义扩展和功能增强。
- **高可靠性**：支持数据的容错和高可用性，确保广告平台的数据处理不间断。

## 系统要求

- 操作系统：Linux / macOS / Windows
- Java 版本：1.8+
- Maven 版本：3.6+
- 数据库：HBase / MySQL / S3 等
- 其他依赖：请参考 `pom.xml` 中的依赖项。

## 安装与配置

### 1. 克隆仓库

```bash
git clone https://github.com/sunlongjiang/adx-data-processing.git
cd adx-data-processing
