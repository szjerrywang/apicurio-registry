/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.registry.ccompat.store;

import io.apicurio.registry.ccompat.dto.SchemaContent;
import io.apicurio.registry.ccompat.dto.Schema;
import io.apicurio.registry.ccompat.dto.SubjectVersion;
import io.apicurio.registry.storage.StoredArtifact;

public class FacadeConverter {

    public static int convertUnsigned(long value) {
        if (value < 0 || value > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Value out of unsigned integer range: " + value);
        }
        return (int) value;
    }

    public static Schema convert(String subject, StoredArtifact storedArtifact) {
        return new Schema(
                convertUnsigned(storedArtifact.getGlobalId()),
                subject,
                convertUnsigned(storedArtifact.getVersion().intValue()),
                storedArtifact.getContent().content()
        );
    }

    public static SchemaContent convert(StoredArtifact artifactVersion) {
        return new SchemaContent(artifactVersion.getContent().content());
    }

    public static SubjectVersion convert(String artifactId, Long version) {
        return new SubjectVersion(artifactId, version);
    }
}
